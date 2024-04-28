package com.siva.jobapp.job.Impl;

import com.siva.jobapp.job.Job;
import com.siva.jobapp.job.JobRepository;
import com.siva.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements JobService {
    JobRepository jobRepository;

    public ServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
       jobRepository.save(job);
    }

    @Override
    public Job findById(Long id) {
       Optional<Job> jobOptional = jobRepository.findById(id);
       if(jobOptional.isPresent())
           return jobOptional.get();
       return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateById(Long id, Job updatedjob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updatedjob.getTitle());
            job.setDescription(updatedjob.getDescription());
            job.setMaxSalary(updatedjob.getMaxSalary());
            job.setMinSalary(updatedjob.getMinSalary());
            job.setLocation(updatedjob.getLocation());
            return true;
        }
        return false;
    }
}
