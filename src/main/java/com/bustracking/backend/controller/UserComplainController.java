package com.bustracking.backend.controller;


import com.bustracking.backend.model.Bus;
import com.bustracking.backend.model.UserComplain;
import com.bustracking.backend.response.ResponseMsg;
import com.bustracking.backend.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
//@CrossOrigin("http://localhost:8080/")
public class UserComplainController {

    @Autowired
    FirebaseInitializer db;

    @GetMapping("/getUserComplains")
    public List<UserComplain> getAllBuses() throws InterruptedException, ExecutionException {
        List<UserComplain> complainsList = new ArrayList<UserComplain>();
        CollectionReference complains = db.getFirebase().collection("user_complains");
        ApiFuture<QuerySnapshot> querySnapshot = complains.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            UserComplain complain = doc.toObject(UserComplain.class);
            complainsList.add(complain);
        }
        return complainsList;
    }

    @Autowired
    ResponseMsg responseMsg;

    @PostMapping("/saveComplain")
    public Object saveComplain(@RequestBody UserComplain userComplain) throws ExecutionException, InterruptedException {

        Firestore fire = FirestoreClient.getFirestore();
        DocumentReference documentReference = fire.collection("user_complains").document();
        userComplain.setComplain_id(documentReference.getId()); //set document reference id to bus_id
        userComplain.setCreated_at(LocalDateTime.now().toString());
        CollectionReference complain_collection = db.getFirebase().collection("user_complains");
        try{
            complain_collection.document(String.valueOf(userComplain.getBus_no())).set(userComplain).isDone();// set document name with bus no
            responseMsg.setMsg("Record Saved.");
            responseMsg.setCreated_time(LocalDateTime.now());
            return responseMsg;
        }catch (Exception e){
            responseMsg.setMsg("Record Not saved.");
            responseMsg.setCreated_time(LocalDateTime.now());
            return responseMsg;
        }








    }
}
