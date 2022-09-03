<template>
    <div class="container mt-5">
        <div class="row align-items-center" v-if="!loading">
            <div class="col-lg-4 mb-5 mb-lg-0">
                <div class="image-wrapper position-relative">
                    <img :src="recipe.thumbnail" class="w-100"/>
                </div>
            </div>
            <div class="col-lg-8 text-left">
                <h5> <strong>Recipe Name</strong></h5>
                <p> {{ recipe.name }}</p>
                <h5><strong>Ingredients</strong></h5>
                <p> {{ recipe.ingredientName }}</p>
                <h5> <strong>Description</strong></h5>
                <p> {{ recipe.description }}</p>
            </div>
        </div>
        <div class="text-center loader" v-else>
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="margin: auto; background: transparent; display: block; shape-rendering: auto;" width="200px" height="200px" viewBox="0 0 100 100" preserveAspectRatio="xMidYMid">
                <circle cx="50" cy="50" fill="none" stroke="#353535" stroke-width="3" r="35" stroke-dasharray="164.93361431346415 56.97787143782138">
                <animateTransform attributeName="transform" type="rotate" repeatCount="indefinite" dur="1s" values="0 50 50;360 50 50" keyTimes="0;1"></animateTransform>
                </circle>
            </svg>
        </div>
      <div class="text-left mb-3">
        <button @click="$router.back()" class="btn btn-outline-secondary btn-large mt-3">Go Back</button>
      </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data(){
        return {
            recipe: null,
            loading: true
        }
    },
    mounted(){
        var recipeName = this.$route.params.uri;
        this.getRecipeInfo(recipeName, this.$route.query.category);
    },
    methods: {
        async getRecipeInfo(recipeName, category = null){
            await axios
                .get("http://localhost:8080/api/recipes/q?name=" + recipeName + "&category=" + category)
                    .then((response) => {
                        this.recipe = response.data[0];
                        this.loading = false;
                });   
        }
    }
}
</script>

<style>

.loader {
        min-height: 92vh;
        display: flex;
        align-items: center;
        justify-content: center;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
}

.image-wrapper img {
        max-height: 600px;
        object-fit: cover;
        object-position: top;
}

</style>