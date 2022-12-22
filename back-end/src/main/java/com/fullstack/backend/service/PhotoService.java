package com.fullstack.backend.service;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface PhotoService {
    String savePhoto(InputStream photo, String titre) throws FlickrException;
}
