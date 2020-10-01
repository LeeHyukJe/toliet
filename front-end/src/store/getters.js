export const user = state => { return { name: 'Hyukje Lee' } }

export const hasBoards = state => {
  return true
}

export const personalBoards = state => {
  // return state.boards.filter(board => board.teamId === 0)
  return [{
    id: 1,
    name: 'vuejs.spring-boot.mysql',
    description: 'An implementation of TaskAgile application with Vue.js, Spring Boot, and MySQL'
  }]
}

export const teamBoards = state => {
  return [{
    id: 1,
    name: 'Sales & Marketing',
    boards: [{
      id: 2,
      name: '2018 Planning',
      description: '2018 sales & marketing planning'
    }, {
      id: 3,
      name: 'Ongoing Campaigns',
      description: '2018 ongoing marketing campaigns'
    }]
  }]
}
