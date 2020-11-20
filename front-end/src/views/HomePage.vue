<template>
  <div>
    <PageHeader />
    <div class="boards-container">
      <div class="boards-section">
        <h2 class="section-title">Personal Boards</h2>
        <div class="boards d-flex align-content-start flex-wrap">
          <div class="board list-inline-item" v-for="board in personalBoards"
               v-bind:key="board.id" @click="openBoard(board)">
            <h3>{{ board.name }}</h3>
            <p>{{ board.description }}</p>
          </div>
          <div class="board add list-inline-item" @click="createBoard()">
            <font-awesome-icon icon="plus" />
            <div>Create New Board</div>
          </div>
        </div>
      </div>
      <div class="boards-section" v-for="team in teamBoards" v-bind:key="team.id">
        <h2 class="section-title">{{ team.name }}</h2>
        <div class="boards d-flex align-content-start flex-wrap">
          <div class="board list-inline-item" v-for="board in team.boards"
               v-bind:key="board.id" @click="openBoard(board)">
            <h3>{{ board.name }}</h3>
            <p>{{ board.description }}</p>
          </div>
          <div class="board add list-inline-item" @click="createBoard(team)">
            <font-awesome-icon icon="plus" />
            <div>Create New Board</div>
          </div>
        </div>
      </div>
      <div class="create-team-wrapper">
        <button class="btn btn-link" @click="createTeam()">+ Create New Team</button>
      </div>
      <div class="boards-section">
        <h2 class="section-title">개찰구 내 화장실 찾기</h2>
        <button @click="findToilet">찾기</button>
        <div v-for="toilet in toiletPosition" v-bind:key="toilet">
          <p>{{toilet}}</p>
        </div>
        <div id="map" style="width:500px; height:400px;"></div>
      </div>
    </div>
    <CreateBoardModal
      :teamId="selectedTeamId"
      @created="onBoardCreated" />
    <CreateTeamModal />
  </div>

</template>

<script>
import $ from 'jquery'
import PageHeader from '@/components/PageHeader.vue'
import { mapGetters } from 'vuex'
import CreateBoardModal from '@/modals/CreateBoardModal.vue'
import CreateTeamModal from '@/modals/CreateTeamModal.vue'

export default {
  name: 'HomePage',
  data () {
    return {
      selectedTeamId: 0
    }
  },
  computed: {
    ...mapGetters([
      'personalBoards',
      'teamBoards',
      'toiletPosition'
    ])
  },
  components: {
    PageHeader,
    CreateBoardModal,
    CreateTeamModal
  },
  methods: {
    openBoard (board) {
      this.$router.push({ name: 'board', params: { boardId: board.id } })
    },
    createBoard (team) {
      this.selectedTeamId = team ? team.id : 0
      $('#createBoardModal').modal('show')
    },
    createTeam () {
      $('#createTeamModal').modal('show')
    },
    onBoardCreated (boardId) {
      this.$router.push({ name: 'board', params: { boardId: boardId } })
    },
    initMap () {
      const container = document.getElementById('map')
      const options = { center: new kakao.maps.LatLng(33.450701, 126.570667), level: 3 }
      const map = new kakao.maps.Map(container, options) // 맵 생성
      // 마커추가
      const marker = new kakao.maps.Marker({ position: map.getCenter() })
      marker.setMap(map)
      return map
    },
    findToilet () {
      this.$store.dispatch('getMapInfo', this.initMap())
    }
  },
  mounted () {
    if (window.kakao && window.kakao.maps) {
      this.initMap()
    } else {
      const script = document.createElement('script')
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap)
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ab4ecf9e2a9676481a6d59d4445acf2b'
      document.head.appendChild(script)
    }
  }
}
</script>

<style lang="scss" scoped>
.boards-container {
  padding: 0 35px;
  h2 {
    font-size: 18px;
    margin-bottom: 15px;
    font-weight: 400;
  }
  .boards-section {
    margin: 30px 10px;
    .boards {
      .board {
        width: 270px;
        height: 110px;
        border-radius: 5px;
        background-color: #377EF6;
        color: #fff;
        padding: 15px;
        margin-right: 10px;
        cursor: pointer;
        h3 {
          font-size: 16px;
        }
        p {
          line-height: 1.2;
          font-size: 90%;
          font-weight: 100;
          color: rgba(255, 255, 255, 0.70)
        }
      }
      .add {
        background-color: #f4f4f4;
        color: #666;
        text-align: center;
        padding-top: 30px;
        font-weight: 400;
      }
    }
  }
  .create-team-wrapper {
    .btn-link {
      color: #666;
      text-decoration: underline;
    }
  }
}
</style>
