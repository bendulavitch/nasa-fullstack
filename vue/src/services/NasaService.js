import axios from 'axios';


const http = axios.create({
    baseURL: 'https://images-api.nasa.gov'
});

export default {
    getCollectionItems(query) {
        return http.get(`search?q=${query}&media_type=image,video`)
    }
}