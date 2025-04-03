<template>
    <div class="neo-view">
      <label for="start-date" class="date-label">Start Date:</label>
      <input type="date" id="start-date" v-model="startDate" class="date-input" />
      <label for="end-date" class="date-label">End Date:</label>
      <input type="date" id="end-date" v-model="endDate" class="date-input" />
      <p class="selected-range">Selected Range: {{ startDate }} - {{ endDate }}</p>
      <button @click="loadNeos" class="find-button">Find the NEOs!</button>
      <NeoComponent :startDate="startDate" :endDate="endDate" v-if="datesEntered" />
    </div>
  </template>
  
  <script>
  import NeoComponent from '../components/NeoComponent.vue'
  export default {
      components: { NeoComponent }, 
      data(){
          return {
              startDate: '',
              endDate: '',
              datesEntered: false
          }
      },
      methods: {
          loadNeos() {
              this.datesEntered = true;
          }
      }
  }
  </script>
  
  <style scoped>
  .neo-view {
    background-color: #1e1e2f;
    color: #d1d1d1;
    padding: 2rem;
    border-radius: 10px;
    max-width: 500px;
    margin: 2rem auto;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
    text-align: center;
  }
  
  .date-label {
    font-size: 1.2rem;
    color: #ccc;
    margin-right: 0.5rem;
    display: inline-block;
    margin-bottom: 0.5rem;
  }
  
  .date-input {
    width: 100%;
    padding: 0.8rem;
    font-size: 1rem;
    border: 1px solid #444;
    border-radius: 5px;
    background-color: #2b2b42;
    color: #d1d1d1;
    margin-top: 0.3rem;
    margin-bottom: 1rem;
  }
  
  .date-input:focus {
    outline: none;
    border: 1px solid #4b86f7;
    background-color: #3a3a56;
  }
  
  .selected-range {
    font-size: 1rem;
    margin-bottom: 1rem;
    color: #ccc;
  }
  
  .find-button {
    background-color: #4b86f7;
    color: #fff;
    padding: 0.8rem 2rem;
    font-size: 1.2rem;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
  }
  
  .find-button:hover {
    background-color: #365cbf;
  }
  
  @media (max-width: 768px) {
    .neo-view {
      padding: 1.5rem;
      max-width: 100%;
    }
  
    .date-input {
      font-size: 1rem;
      padding: 0.7rem;
    }
  
    .find-button {
      font-size: 1rem;
      padding: 0.7rem 1.5rem;
    }
  }
  </style>
  