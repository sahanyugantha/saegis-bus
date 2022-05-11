package com.bustracking.backend.controller;

import com.bustracking.backend.model.Bus;
import com.bustracking.backend.response.ResponseMsg;
import com.bustracking.backend.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
//@CrossOrigin("http://localhost:8080/")
public class BusController {



    public BusController( ResponseMsg responseMsg) {
        this.responseMsg = responseMsg;
       // FirebaseInitializer fb = new FirebaseInitializer();
    }

    @GetMapping("/getAllBuses")
    public List<Bus> getAllBuses() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        List<Bus> busList = new ArrayList<Bus>();
        CollectionReference buses = db.collection("buses");
        ApiFuture<QuerySnapshot> querySnapshot = buses.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            Bus bus = doc.toObject(Bus.class);
            busList.add(bus);
        }
        return busList;


    }

    final
    ResponseMsg responseMsg;

    @PostMapping("/saveBus")
    public Object saveBus(@RequestBody Bus bus) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        Firestore fire = FirestoreClient.getFirestore();
        DocumentReference documentReference = fire.collection("buses").document();
        bus.setBus_id(documentReference.getId()); //set document reference id to bus_id

        DocumentReference docRef = fire.collection("buses").document(bus.getBus_no());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {


            responseMsg.setMsg("Bus Number already exist.");
            responseMsg.setCreated_time(LocalDateTime.now());
            return responseMsg;
        } else {
            CollectionReference employeeCR = db.collection("buses");
            employeeCR.document(String.valueOf(bus.getBus_no())).set(bus);// set document name with bus no

            responseMsg.setMsg("Record Saved.");
            responseMsg.setCreated_time(LocalDateTime.now());
            return responseMsg;
        }


    }

    @PutMapping("/updateBus")
    public String updateBus(@RequestBody Bus bus) throws ExecutionException, InterruptedException {
        Firestore fire = FirestoreClient.getFirestore();
        DocumentReference docRef = fire.collection("buses").document(bus.getBus_no());

        ApiFuture<WriteResult> writeResult = docRef.set(bus);

        return bus.getBus_no() + " updated successfully";
    }

    @DeleteMapping("/deleteBus")
    public String deleteBus(@RequestParam String bus_no) throws ExecutionException, InterruptedException {
        Firestore fire = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = fire.collection("buses").document(bus_no).delete();
        return "Successfully deleted" + bus_no;
    }

    @GetMapping("/searchBus")
    public List<Bus> searchBus(@RequestParam String bus_no) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        List<Bus> searchbusList = new ArrayList<Bus>();
        CollectionReference buses = db.collection("buses");
        ApiFuture<QuerySnapshot> querySnapshot = buses.whereEqualTo("bus_no", bus_no).get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            Bus bus = doc.toObject(Bus.class);
            searchbusList.add(bus);
        }

        return searchbusList;


    }

    //this endpoint for mobile application
    @GetMapping("/filterBus")
    public List<Bus> filterBus(@RequestParam String user_destination) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        List<Bus> busList = new ArrayList<Bus>();
        CollectionReference buses = db.collection("buses");
        ApiFuture<QuerySnapshot> querySnapshot = buses.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            String data = doc.getString("location_set");
            if (data.contains(user_destination)) {
                System.out.println("available");
                Bus bus = doc.toObject(Bus.class);
                busList.add(bus);
            }


        }
        return busList;


    }

}
