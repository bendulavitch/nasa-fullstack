<template>
    <div class="neo-component">
      <ul class="neo-list">
        <li v-for="neo in neoList" :key="neo.id" class="neo-item">{{ neo.name }}</li>
      </ul>
    </div>
  </template>
  
  <script>
  import service from '../services/AuthService'
  
  export default {
      props: {
          startDate: {
              type: String,
              required: true
          },
          endDate: {
              type: String,
              required: true
          }
      },
      data() {
          return {
              neoList: []
          }
      },
      created() {
          this.fetchNeos();
      },
      watch: {
          startDate(newStartDate) {
              this.fetchNeos(newStartDate, this.endDate);
          },
          endDate(newEndDate) {
              this.fetchNeos(this.startDate, newEndDate);
          }
      },
      methods: {
          fetchNeos(startDate = this.startDate, endDate = this.endDate) {
              service.getNeos(startDate, endDate)
                  .then((response) => {
                      this.neoList = response.data;
                  })
                  .catch((error) => {
                      console.error("Error fetching Neos:", error);
                  });
          }
      }
  }
  </script>
  
  <style scoped>
  .neo-component {
    background-color: #1e1e2f;
    color: #d1d1d1;
    padding: 2rem;
    border-radius: 10px;
    max-width: 600px;
    margin: 2rem auto;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
  }
  
  .neo-list {
    list-style-type: none;
    padding: 0;
  }
  
  .neo-item {
    background-color: #2b2b42;
    color: #ccc;
    padding: 1rem;
    margin: 0.5rem 0;
    border-radius: 5px;
    text-align: center;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
    transition: background-color 0.3s ease;
  }
  
  .neo-item:hover {
    background-color: #4b86f7;
    color: #fff;
  }
  
  @media (max-width: 768px) {
    .neo-component {
      padding: 1.5rem;
      max-width: 100%;
    }
  
    .neo-item {
      font-size: 1rem;
    }
  }
  </style>
  