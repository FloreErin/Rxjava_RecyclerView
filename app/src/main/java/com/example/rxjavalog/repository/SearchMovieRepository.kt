package com.example.rxjavalog.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.rxjavalog.model.ResultGetSearchMovie
import com.example.rxjavalog.model.TmdbMovieClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val TMDB_API_KEY : String = "07a7465ce3c9d6c2ee2193948869ee8b"
private const val TAG = "Observable Tag"

class SearchMovieRepository {
    // Observable
    @SuppressLint("CheckResult")
    fun getMovieList(query : String, page : Int) : Observable<ResultGetSearchMovie> {
        return TmdbMovieClient().retrofit.getSearchMovie(TMDB_API_KEY, "ko-KR", query, page, false)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { data ->
                Log.d(TAG, "Retrofit Observable onNext : $data")
            }
            .doOnError { error ->
                Log.e(TAG, "Retrofit Observable Error : $error")
            }
    }
}