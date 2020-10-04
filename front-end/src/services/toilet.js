import axios from 'axios'

export default {
  findToilet (map) {
    const center = map.getCenter()
    const mapInfo = {
      x: center.getLng(),
      y: center.getLat()
    }
    return new Promise((resolve, reject) => {
      axios.post('/calculation/distance', mapInfo).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(error)
      })
    })
  }
}
