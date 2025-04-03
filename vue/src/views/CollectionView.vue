<template>
  <div class="search-input">
    <input
      type="text"
      v-model="searchTerm"
      placeholder="Enter search term..."
      @keyup.enter="performSearch"
    />
    <button @click="performSearch">Search</button>

    <CollectionComponent v-if="searchPerformed" :collectionQuery="searchTerm" />
  </div>
</template>

<script>
import CollectionComponent from '../components/CollectionComponent.vue'

export default {
  components: {
    CollectionComponent
  },

  data() {
    return {
      searchTerm: '',
      searchPerformed: false
    }
  },
  methods: {
    performSearch() {
      if (this.searchTerm.trim() !== '') {
        this.searchPerformed = true;
      }
    }
  }
}
</script>

<style scoped>
.search-input {
  padding: 20px;
}

.search-input input {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px 0 0 5px;
  margin-right: 0px;
}

.search-input button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  margin-left: 10px;
}
</style>
