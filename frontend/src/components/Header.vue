<template>
    <header>
        <div class="container">
            <div class="header-wrapper">
                    <div id="nav">
                        <router-link to="/home">Home</router-link>
                        <router-link to="/dish">Main Dishes</router-link>
                        <router-link to="/salad">Salads</router-link>
                        <router-link to="/dessert">Desserts</router-link>
                        <router-link to="/drink">Drinks</router-link>
                    </div>
              <img src="/images/logo.jpg"/>
            </div>
        </div>
    </header>
</template>

<style scoped>
    .header-wrapper  {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    header {
        padding: 10px 0;
        background: #d7B592;
    }

    header #nav a {
        font-family: 'Poppins', sans-serif;
        font-weight: 500;
        font-size: 17px;
        color: #ffffff;
        padding: 10px 15px;
        text-decoration: none;
    }
    header #nav a:not(:last-of-type){
        margin-right: 15px;
    }

    header img {
        max-width: 50px;
    }
</style>