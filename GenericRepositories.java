package com.example.sample_log_app;

import retrofit2.Response;

public abstract class GenericRepositories {
    public abstract <T extends GenericEntity> void onSuccessResponse(Response<T> entity);

    public abstract void onFailureResponse(Throwable t);
}
