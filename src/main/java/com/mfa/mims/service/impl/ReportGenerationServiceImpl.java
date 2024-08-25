package com.mfa.mims.service.impl;

import com.mfa.mims.service.ReportGenerationService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ReportGenerationServiceImpl implements ReportGenerationService {

    @Override
    @Async(value = "notificationTaskExecutor")
//    @Async
    public CompletableFuture<String> generateReport(String reportType) {

        return CompletableFuture.supplyAsync
        (
            ()->
            {
                //Creating a new thread for report generation
                Thread reportThread = new Thread
                        (
                                ()->
                                {
                                    try
                                    {
                                        System.out.println("Thread "+Thread.currentThread().getName()+" started");

                                        //Simulate long running task
                                        Thread.sleep(5000);

                                        System.out.println("Thread "+Thread.currentThread().getName()+" completed");

                                    }
                                    catch (InterruptedException interruptedException)
                                    {
                                        System.out.println("Thread "+Thread.currentThread().getName()+" occurred");
                                    }

                                }
                        );

                //Log thread state and name before starting the thread
                System.out.println("Thread state before start: "+reportThread.getState());
                System.out.println("Thread Name: "+reportThread.getName());

                //Start the thread
                reportThread.start();

                //Log thread state after starting
                System.out.println("Thread state after starting: "+reportThread.getState());

                try
                {
                    //Join the thread to ensure it completes before proceeding
                    reportThread.join();

                }
                catch (InterruptedException interruptedException)
                {
                    System.out.println("Main thread was interrupted.");
                }

                //Log thread state after completion
                System.out.println("Thread state after join: "+reportThread.getState());

                //Return success message
//                return "report generation for " +reportThread+ "was completed";
                return "report generation for " +reportThread+ "was completed";
            }
        );
    }
}

