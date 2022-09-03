<template>
  <div class="recipes-section">
    <div class="container">
     <h3>Drink Recipes</h3>

    <div class="row mb-5">
      <div class="offset-lg-3 col-lg-4">
        <input type="text" class="form-control" id="searchRecipe" placeholder="Search recipe" v-model="searchInput" @keyup.enter="searchRecipe">
      </div>
       <div class="col-lg-2">
         <button class="btn btn-outline-secondary" @click="searchRecipe">Search Recipes</button>
       </div>
     </div>

    <div class="row" v-if="!loading">
      <div class="col-lg-4">
        <div v-if="!loading" class="widgets">
          <div class="widget text-left" style="background: white">
            <i class="far fa-lemon"></i>
            <div class="widget-text">
              <h4>Category</h4>
              <p class="lead">{{ course.label }}</p>
            </div>
          </div>
          <div class="widget text-left" style="background: white">
            <i class="far fa-lemon"></i>
            <div class="widget-text">
              <h4>Description</h4>
              <p class="lead">{{ course.description }}</p>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-8">
        <div class="row all-recipes" v-if="!loading">
          <div class="col-lg-4" v-for="course in course.recipes" :key="course.name">
              <div class="recipe-wrapper">
                <router-link :to=getRecipeLink(course.name)>
                  <img v-if="course.thumbnail" :src="course.thumbnail" alt="">
                  <img v-else src="https://via.placeholder.com/600x800">
                  <h5>{{ course.name }}</h5>
                </router-link>
              </div>
          </div>
      </div>
      </div>
    </div>
      <div class="loader text-center" v-else>
        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="margin: auto; background: transparent; display: block; shape-rendering: auto;" width="200px" height="200px" viewBox="0 0 100 100" preserveAspectRatio="xMidYMid">
          <circle cx="50" cy="50" fill="none" stroke="#353535" stroke-width="3" r="35" stroke-dasharray="164.93361431346415 56.97787143782138">
            <animateTransform attributeName="transform" type="rotate" repeatCount="indefinite" dur="1s" values="0 50 50;360 50 50" keyTimes="0;1"></animateTransform>
          </circle>
        </svg>
      </div>
    </div>
  </div>

</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      course: null,
      searchInput: "",
      loading: true,
      filteredRecipes: null
    };
  },
  created() {
    this.getDrinkRecipes();
  },
  methods: {
    async getDrinkRecipes() {
      await axios
        .get("http://localhost:8080/api/courses/search?course=Drink")
        .then((response) => {
          this.course = response.data;
          this.loading = false;
          console.log(response.data);
        });     
    },
    getRecipeLink(recipe){
      return "/recipes/" + encodeURIComponent(recipe.replace('_', '%20')) + "?category=drink";
    },
    async searchRecipe(){
      this.loading = true;
      await axios
        .get("http://localhost:8080/api/recipes/q?name=" + this.searchInput + "&category=drink")
        .then((response) => {
          this.course.recipes = response.data;
          this.loading = false;
        });   
    }
  },
};
</script>

<style>

.widget {
  display: flex;
  align-items: center;
  padding: 20px 30px;
  margin-bottom: 15px;
  font-family: 'Poppins', sans-serif;
}

.widgets {
  background: burlywood;
}

.widget h4 {
  font-weight: 900;
  line-height: 1.1;
}

.widget h4,
.widget p {
  margin-bottom: 0;
}

.widget i {
  font-size:32px;
  margin-right: 20px;
}

.recipes-section {
    padding: 100px 0;
}

.recipe-wrapper {
    margin-bottom: 50px;
}

.recipe-wrapper img {
    width: 100%;
    height: 500px;
    object-fit: cover;
    margin-bottom: 10px;
}

</style>
