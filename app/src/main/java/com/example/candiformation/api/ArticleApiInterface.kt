package com.example.candiformation.api

import com.example.candiformation.models.*
import retrofit2.http.*

interface ArticleApiInterface {

    // 모든 article 불러오기
    @GET("api/articles")
    suspend fun getArticle(): List<ArticleResponse> // Article list를 return

    // 회원가입
    @POST("api/users")
    suspend fun signUp(
        @Body body: SignUpBody
    ): SignUpResponse

    // 로그인
    @POST("api/users/login")
    suspend fun signIn(
        @Body body: LoginBody
    ): LoginResponse

    // 좋아요 버튼 클릭
    @PUT("api/articles")
    suspend fun like(
        @Body body: LikeBody,
        @HeaderMap header: Map<String, String>
    ): LikeResponse

    // 댓글 달기
    @POST("api/comments")
    suspend fun writeComment(
        @Body body: CommentBody,
        @HeaderMap header: Map<String, String>
    ): LikeResponse
}