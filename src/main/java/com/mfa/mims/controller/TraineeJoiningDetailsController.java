package com.mfa.mims.controller;

import com.mfa.mims.entity.TraineeJoiningDetails;
import com.mfa.mims.repository.TraineeJoiningDetailsRepository;
import com.mfa.mims.service.TraineeJoiningDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forms-trainee-joining-details")
public class TraineeJoiningDetailsController {

    @Autowired
    private TraineeJoiningDetailsService traineeJoiningDetailsService;

    @PostMapping
    public ResponseEntity<TraineeJoiningDetails> createTraineeJoiningDetails(@RequestBody TraineeJoiningDetails traineeJoiningDetails)
    {
        return ResponseEntity.ok(traineeJoiningDetailsService.createTraineeJoiningDetails(traineeJoiningDetails));
    }

    @GetMapping
    public ResponseEntity<List<TraineeJoiningDetails>> getAllTraineeJoiningDetails()
    {
        List<TraineeJoiningDetails> listOfTraineeJoiningDetails = traineeJoiningDetailsService.getAllTraineeJoiningDetails();
        return ResponseEntity.ok(listOfTraineeJoiningDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TraineeJoiningDetails> getTraineeJoiningDetailsById(@PathVariable Long id)
    {
        Optional<TraineeJoiningDetails> optionalTraineeJoiningDetails = traineeJoiningDetailsService.getTraineeJoiningDetailsById(id);
        TraineeJoiningDetails traineeJoiningDetails = optionalTraineeJoiningDetails.get();
        if (traineeJoiningDetails.getId() == id)
            return ResponseEntity.ok(traineeJoiningDetails);
        else
            return ResponseEntity.notFound().build();
   }


   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteTraineeJoiningDetailsById(@PathVariable Long id)
   {
       Optional<TraineeJoiningDetails> optionalTraineeJoiningDetails = traineeJoiningDetailsService.getTraineeJoiningDetailsById(id);
       TraineeJoiningDetails traineeJoiningDetails = optionalTraineeJoiningDetails.get();
       if (traineeJoiningDetails.getId() == id)
       {
           traineeJoiningDetailsService.deleteTraineeJoiningDetailsById(id);
           return ResponseEntity.ok("Trainee with id "+id+ " deleted Successfully!");
       }

       else
          return ResponseEntity.status(500).build();

   }






    @PutMapping("/{id}")
    public ResponseEntity<TraineeJoiningDetails> updateTraineeJoiningDetails(@PathVariable Long id, @RequestBody TraineeJoiningDetails traineeJoiningDetails) {
        return traineeJoiningDetailsService.updateTraineeJoiningDetails(id, traineeJoiningDetails)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
