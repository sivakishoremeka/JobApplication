package com.siva.jobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(companyId,review);
        if(isReviewSaved)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>("Company Doesn't exists",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(companyId,reviewId), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId,@PathVariable Long reviewId,
                                                   @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(companyId,reviewId,review);
        if(isReviewUpdated)
            return new ResponseEntity<>("Review Updatd Successfully", HttpStatus.OK);
        return  new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReviewById(companyId,reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review deleted Successfuly",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not deleted",HttpStatus.NOT_FOUND);
    }
}
