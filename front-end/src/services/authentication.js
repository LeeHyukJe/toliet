import axios from 'axios'
export default {
  authenticate (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/authentications', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(error)
      })
    })
  }
}
