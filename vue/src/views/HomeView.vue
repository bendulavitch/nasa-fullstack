<template>
  <div class="home">
    <h1>Welcome to the Nasa App</h1>
  </div>
</template>

<script>
export default {
};
</script>

<style scoped>
.home {
  background-color: #1e1e2f;
  color: #d1d1d1;
  padding: 3rem;
  text-align: center;
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
  max-width: 600px;
  margin: 2rem auto;
}

h1 {
  font-family: 'Orbitron', sans-serif;
  font-size: 3rem;
  color: #4b86f7;
  margin-bottom: 1.5rem;
}

p {
  font-size: 1.5rem;
  color: #ccc;
  margin-top: 1rem;
  padding: 0.5rem;
  background-color: #2b2b42;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

@media (max-width: 768px) {
  .home {
    padding: 2rem;
    max-width: 100%;
  }

  h1 {
    font-size: 2.5rem;
  }

  p {
    font-size: 1.2rem;
  }
}
</style>
