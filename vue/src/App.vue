<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" class="nav-link">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'collection' }" class="nav-link">Collections</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'neo' }" class="nav-link">Near Earth Objects</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="nav-link">Logout</router-link>
    </div>
    
    <router-view />
  </div>
</template>

<style scoped>
#capstone-app {
  text-align: center;
  font-family: 'Ubuntu', Arial, Helvetica, sans-serif;
  background-color: #1b1b2f;
  color: #fff;
  min-height: 100vh;
}

#nav {
  background-color: #282a36;
  padding: 15px 0;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
}

#nav a {
  color: #fff;
  font-size: 1.2em;
  text-decoration: none;
  padding: 5px 15px;
  border-radius: 5px;
  transition: background-color 0.3s ease, color 0.3s ease;
}

#nav a:hover {
  background-color: #007bff;
  color: #fff;
}

#nav a.active {
  background-color: #ff6347;
}

#nav a:focus {
  outline: none;
}

#capstone-app {
  background-color: #1b1b2f;
  min-height: 100vh;
}

router-view {
  padding: 20px;
}
</style>
