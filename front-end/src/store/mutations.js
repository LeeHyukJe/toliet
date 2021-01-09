export default {
  updateMyData (state, data) {
    state.user.name = data.user.name
    state.teams = data.teams
    state.boards = data.boards
  },
  addTeam (state, team) {
    state.teams.push(team)
  },
  addBoards (state, board) {
    state.boards.push(board)
  },
  addMapInfo (state, mapInfo) {
    state.toilets.push(mapInfo)
  }
}
