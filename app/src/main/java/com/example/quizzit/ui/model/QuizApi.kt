package com.example.quizzit.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val retrofit = Retrofit.Builder().baseUrl("https://opentdb.com/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val questionService: QuizApi = retrofit.create(QuizApi::class.java)

val categoryService: CategoryApi = retrofit.create(CategoryApi::class.java)

interface QuizApi {
    @GET("api.php")  // Endpoint path without query parameters
    suspend fun getQuestion(
        @Query("amount") amount: Int = 10,              // Fixed query parameter
        @Query("type") type: String = "multiple",       // Fixed query parameter
        @Query("category") category: Int                // Dynamic query parameter
    ): QuestionResponse
}

interface CategoryApi{
    @GET("api_category.php")
    suspend fun getCategories(): CategoryResponse
}

//Data Classes for Api.
@Parcelize
data class Question(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
): Parcelable

@Parcelize
data class Category(
    val id:Int,
    val name: String
): Parcelable

data class QuestionResponse(val response_code:Int, val results:List<Question> )

data class CategoryResponse(val trivia_categories: List<Category>)


//JSON from https://opentdb.com/api.php.
//"type": "multiple",
//"difficulty": "easy",
//"category": "General Knowledge",
//"question": "What is the Spanish word for &quot;donkey&quot;?",
//"correct_answer": "Burro",
//"incorrect_answers": [
//"Caballo",
//"Toro",
//"Perro"
//]

// JSON from https://opentdb.com/api_category.php
//"trivia_categories": [
//{
//    "id": 9,
//    "name": "General Knowledge"
//},]