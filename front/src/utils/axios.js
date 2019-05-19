import axios from "axios"

const instance = axios.create({
    baseURL: process.env.REACT_APP_API_URL,
    timeout: 10000,
    headers: {
        Authorization: localStorage.getItem('token')
    }
});


export default instance
