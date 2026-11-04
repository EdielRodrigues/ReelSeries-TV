package com.reelseries.android;
import android.content.*;
import com.google.firebase.auth.*;
import com.google.firebase.firestore.*;
import java.util.*;
public final class CloudUserState {
 private CloudUserState(){}
 private static final String[] STORES={"reel_favorites","reel_continue_history","reel_playback_progress","reel_watched_episodes"};
 private static final Map<String,ListenerRegistration> listeners=new HashMap<>();
 private static final Map<String,Long> lastSent=new HashMap<>();
 private static FirebaseAuth.AuthStateListener authListener;private static String activeUid;
 private static String uid(){FirebaseUser u=FirebaseAuth.getInstance().getCurrentUser();return u==null?null:u.getUid();}
 private static CollectionReference ref(String uid,String store){return FirebaseFirestore.getInstance().collection("usuarios").document(uid).collection("estado").document(store).collection("itens");}
 private static String keyId(String key){try{byte[] hash=java.security.MessageDigest.getInstance("SHA-256").digest(key.getBytes("UTF-8"));StringBuilder b=new StringBuilder("k");for(byte v:hash)b.append(String.format(java.util.Locale.US,"%02x",v&255));return b.toString();}catch(Exception e){return "k"+Integer.toHexString(key.hashCode());}}
 public static synchronized void start(Context ctx){Context app=ctx.getApplicationContext();if(authListener!=null)return;
  authListener=auth->{synchronized(CloudUserState.class){for(ListenerRegistration reg:listeners.values())reg.remove();listeners.clear();lastSent.clear();FirebaseUser user=auth.getCurrentUser();String next=user==null?null:user.getUid();
   if(!java.util.Objects.equals(activeUid,next)){activeUid=next;}
   if(user!=null)for(String store:STORES)listen(app,user.getUid(),store);}};
  FirebaseAuth.getInstance().addAuthStateListener(authListener);
 }
 private static void listen(Context app,String uid,String store){
  CollectionReference col=ref(uid,store);SharedPreferences prefs=app.getSharedPreferences(store,Context.MODE_PRIVATE);
  ListenerRegistration reg=col.addSnapshotListener((snap,error)->{
   if(error!=null||snap==null||!uid.equals(uid()))return;
   SharedPreferences.Editor ed=prefs.edit();
   for(DocumentSnapshot d:snap.getDocuments()){
    String k=d.getString("key");if(k==null||Boolean.TRUE.equals(d.getBoolean("deleted")))continue;
    Object v=d.get("value");if(v instanceof Boolean)ed.putBoolean(k,(Boolean)v);
    else if(v instanceof Number)ed.putLong(k,((Number)v).longValue());
    else if(v instanceof String)ed.putString(k,(String)v);
   }
   ed.apply();app.sendBroadcast(new Intent("com.reelseries.android.CLOUD_STATE_CHANGED").setPackage(app.getPackageName()));
  });listeners.put(uid+"/"+store,reg);
 }
 public static void put(Context c,String store,String key,Object value){String uid=uid();if(uid==null||key==null)return;start(c);Map<String,Object> m=new HashMap<>();m.put("key",key);m.put("value",value);m.put("deleted",false);m.put("updated",System.currentTimeMillis());ref(uid,store).document(keyId(key)).set(m);}
 public static void remove(Context c,String store,String key){String uid=uid();if(uid==null||key==null)return;start(c);Map<String,Object> m=new HashMap<>();m.put("key",key);m.put("deleted",true);m.put("updated",System.currentTimeMillis());ref(uid,store).document(keyId(key)).set(m);}
 public static synchronized void progress(Context c,String key,long pos,String history){String uid=uid();if(uid==null)return;long now=System.currentTimeMillis();String tag=uid+"/"+key;Long previous=lastSent.get(tag);if(previous!=null&&now-previous<15000)return;lastSent.put(tag,now);put(c,"reel_playback_progress",key,pos);if(history!=null)put(c,"reel_continue_history",key,history);}
}
