<template xmlns:text-align="http://www.w3.org/1999/xhtml">
  <div class="home">
    <div class="home-wrapper" v-if="!loading">
      <div class="hero-section">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-lg-6 text-left">
              <img src="/images/logoo.png" width="300" height="350"/>
              <h1 v-if="!loading">{{ food.name }}</h1>
              <p class="lead" v-if="!loading">
                {{
                   food.description.substr(0, 275)
                }}
              </p>
              <div class="scroll">
                  <i class="fa fa-chevron-down"></i>
                  <span>Scroll down</span>
              </div>
            </div>
            <div class="col-lg-6">
                <div class="row categories">
                    <div class="col-lg-6">
                        <div class="category-wrapper">
                            <router-link to="/dish"><img src="/images/dish.jpg"/><span>Main Dishes</span></router-link>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="category-wrapper">
                            <router-link to="/salad"><img src="/images/salad.jpg"/><span>Salads</span></router-link>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="category-wrapper">
                            <router-link to="/dessert"><img src="/images/dessert.jpg"/><span>Desserts</span></router-link>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="category-wrapper">
                            <router-link to="/drink"><img src="/images/drink.jpg"/><span>Drinks</span></router-link>
                        </div>
                    </div>
                </div>
            </div>
          </div>
        </div>
      </div>
      <div class="about-section">
          <div class="container">
              <div class="row">
                  <div class="col-lg-6 text-left">
                      <h2>About Food and Its Significances</h2>
                      <p v-if="food != null"> {{ food.description}} </p>
                      <p v-else>Loading...</p>
                  </div>
                  <div class="col-lg-6">
                      <img v-if="food != null" :src="food.thumbnail" alt="">
                      <p v-else> Loading... </p>
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
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      food: null,
      loading: true
    };
  },
  created() {
    this.getFoodInfo();
  },
  methods: {
    async getFoodInfo() {
      await axios
        .get("http://localhost:8080/api/recipes/search?recipe=Food")
        .then((response) => {
          this.food = response.data;
          this.loading = false;
        });     
    },
  },
};
</script>

<style scoped>

.category-wrapper {
    background: #ffffff;
}

.category-wrapper a {
    display: flex;
    flex-direction: column;
    text-decoration: none;
    color: #000000;
    font-weight: 500;
}

.category-wrapper i {
    font-size: 60px;
}

.category-wrapper span {
    font-family: "Poppins", sans-serif;
}

.scroll {
    margin-top: 30px;
    display: flex;
    align-items: center;

}
.scroll i {
    width: 60px;
    height: 60px;
    border-radius: 50%;
    font-size: 30px;
    border: 2px solid #000000;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-right: 30px;
    animation: pulse 2s ease-in-out infinite;
}

@keyframes pulse {
    0% {
        transform: scale(1.1);
    } 
    50% {
        transform: scale(0.9);
    }
    100% {
        transform: scale(1.1);
    }
}

h1 {
  font-size: 62px;
  font-weight: 700;
}

.hero-section {
  min-height: 90vh;
  padding: 30px 0 200px;
  display: flex;
  align-items: center;
}

</style>
