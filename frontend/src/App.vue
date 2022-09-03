<template>
  <Header/>
  <router-view/>
</template>

<script>
import Header from './components/Header.vue'
export default {
  name: 'App',
  components: { Header }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;800&display=swap');

html, body {
  padding: 0;
  margin: 0
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
