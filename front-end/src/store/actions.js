import meService from '@/services/me'

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

export const addMapInfo = ({ commit }, mapInfo) => {
  commit('addMapInfo', mapInfo)
}
