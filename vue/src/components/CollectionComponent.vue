<template>
  <div class="collection-view">
    <h1 class="collection-title">{{ collectionTitle }}</h1>

    <div class="media-grid">
      <div v-for="item in collectionItems" :key="item.data[0].nasa_id" class="media-item">
        <div v-if="item.data[0].media_type === 'image'" class="media-thumbnail">
          <img :src="item.links[0].href" :alt="item.data[0].title" />
        </div>
        <div v-else-if="item.data[0].media_type === 'video'" class="media-thumbnail">
          <video :src="item.links[0].href" controls>
            Your browser does not support the video tag.
          </video>
        </div>
        <div class="media-details">
          <h3 class="media-title">{{ item.data[0].title }}</h3>
          <p class="media-date">{{ item.data[0].date_created }}</p>
          <p class="media-description">{{ item.data[0].description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import service from '../services/NasaService'

export default {
    data() {
        return {
            collectionItems: [],
            collectionTitle: ''
        }
    },
    props: {
        collectionQuery: String
    },
    created() {
        service.getCollectionItems(this.collectionQuery)
        .then((response) => {
            console.log(response);
            this.collectionItems = response.data.collection.items;
            if (this.collectionItems.length > 0) {
                this.collectionTitle = this.collectionItems[0].data[0].title;
            }
        })
    }
}
</script>

<style scoped>
.collection-view {
  padding: 20px;
  background-color: #2b2b42;
  border-radius: 10px;
  max-width: 1200px;
  margin: 0 auto;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.8);
}

.collection-title {
  text-align: center;
  margin-bottom: 20px;
  color: #f0f0f0;
  font-size: 1.8em;
}

.media-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
  justify-items: center;
}

.media-item {
  background-color: #3a3b45;
  border-radius: 10px;
  padding: 15px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.6);
  color: #ddd;
  transition: transform 0.3s ease-in-out;
}

.media-item:hover {
  transform: translateY(-5px);
}

.media-thumbnail {
  width: 100%;
  height: auto;
  margin-bottom: 15px;
  border-radius: 8px;
  overflow: hidden;
}

.media-thumbnail img,
.media-thumbnail video {
  width: 100%;
  height: auto;
}

.media-details {
  text-align: left;
}

.media-title {
  font-size: 1.2em;
  margin-bottom: 5px;
  color: #f0f0f0;
}

.media-date {
  font-size: 0.9em;
  color: #bbb;
  margin-bottom: 10px;
}

.media-description {
  font-size: 0.95em;
  color: #ccc;
  line-height: 1.4;
}

@media (max-width: 768px) {
  .collection-view {
    padding: 15px;
  }

  .collection-title {
    font-size: 1.6em;
  }

  .media-item {
    padding: 12px;
  }
}
</style>
