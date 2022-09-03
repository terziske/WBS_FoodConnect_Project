import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Dish from '../views/Dish.vue';
import Salad from '../views/Salad.vue';
import Dessert from '../views/Dessert.vue';
import Drink from '../views/Drink.vue';
import SingleRecipe from '../views/SingleRecipe.vue';

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    alias: "/home",
  },
  {
    path: "/dish",
    name: "Dish",
    component: Dish,
  },
  {
    path: "/salad",
    name: "Salad",
    component: Salad,
  },
  {
    path: "/dessert",
    name: "Dessert",
    component: Dessert,
  },
  {
    path: "/drink",
    name: "Drink",
    component: Drink,
  },
  {
    path: "/recipes/:uri",
    name: "Recipe",
    component: SingleRecipe,
  },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;