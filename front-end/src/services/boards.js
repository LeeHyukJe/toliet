import axios from 'axios'

export default {
  create (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/boards', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(error)
      })
    })
  }
}
