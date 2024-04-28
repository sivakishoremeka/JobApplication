package com.siva.jobapp.review;


import java.util.List;

public interface ReviewService {
    List<Review> getAllReview(Long companyId);

    boolean addReview(Long companyId, Review review);

    Review getReviewById(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId,Review review);

    boolean deleteReviewById(Long companyId, Long reviewId);
}
