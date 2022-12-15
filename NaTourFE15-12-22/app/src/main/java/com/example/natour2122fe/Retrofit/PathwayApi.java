package com.example.natour2122fe.Retrofit;

import com.example.natour2122fe.Model.Feedback;
import com.example.natour2122fe.Model.InterestPoints;
import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.Model.PathwaySignaling;
import com.example.natour2122fe.Model.Photo;
import com.example.natour2122fe.Model.RespondSignaling;
import com.example.natour2122fe.Model.User;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.io.File;
import java.util.List;
import java.util.Observable;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PathwayApi {
    @GET("/all/viewPathway")
    Call<List<Pathway>> viewAllPathway();

    @GET("/{idPathway}/viewFeedback")
    Call<List<Feedback>> viewFeedback(@Path("idPathway") Integer idPathway);

    @GET("/{idPathway}/viewInterestPoints")
    Call<List<InterestPoints>> viewInterestPoints(@Path("idPathway") Integer idPathway);

    @GET("/{idPathway}/contPathwaySignaling")
    Call<Integer> contPathwaySignaling(@Path("idPathway") Integer idPathway);

    @GET("/viewPathwaysSign")
    Call<List<PathwaySignaling>> viewPathwaySign();

    @GET("/{username}/viewRespondPathwaySign")
    Call<List<RespondSignaling>> respondSignaling(@Path("username") String username);

    @GET("/photo/{idPathway}/viewPhoto")
    Call<List<Photo>> viewPhoto(@Path("idPathway") Integer idPathway);

    @GET("/{username}/viewUser")
    Call<User> viewUser(@Path("username") String username);

    @POST("/all/insertPathways")
    Call<Void> addPathway(@Body Pathway newPathway);

    @POST("/all/addFeedback")
    Call<Void> addFeedback(@Body Feedback newFeedback);

    @POST("/all/addInterestPoints")
    Call<Void> addInterestPoint(@Body InterestPoints newInterestPoint);

    @POST("/all/addPathwaySignaling")
    Call<Void> addPathwaySignaling(@Body PathwaySignaling newPathwaySignaling);

    @POST("/all/addRespondSignaling")
    Call<Void> addRespondSignaling(@Body RespondSignaling newRespondSignaling);

    @Multipart
    @POST("/photo/upload")
    Call<ResponseBody> update(@Query(value = "photo") String key, @Part MultipartBody.Part file);

    @POST("/all/addUser")
    Call<Void> addUser(@Body User user);

    @POST("/photo/all/addPhoto")
    Call<ResponseBody> addPhoto(@Body Photo newPhoto);

}
