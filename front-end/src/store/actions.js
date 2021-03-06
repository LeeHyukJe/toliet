import meService from '@/services/me'
import toiletService from '@/services/toilet'

export const getMyData = ({ commit }) => {
  meService.getMyData().then(data => {
    commit('updateMyData', data)
  })
}

export const addTeam = ({ commit }, team) => {
  commit('addTeam', team)
}

export const addBoard = ({ commit }, board) => {
  commit('addBoards', board)
}

export const getMapInfo = ({ commit }, mapInfo) => {
  mapInfo.then((result) => {
    toiletService.findToilet(result).then(mapInfo => {
      commit('addMapInfo', mapInfo)
    })
  }).catch((error) => {
    console.log(error)
  })
}
