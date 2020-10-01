<template>
  <div>
    가까운 역 찾기 <input @click="findNearByStation()" type="button" value="찾기"/>
    <div id="nearestStation"></div>
    <div id="map" style="width:500px;height:400px;"></div>
  </div>
</template>

<script>
export default {
  name: 'KakaoMap',
  mounted () {
    if (window.kakao && window.kakao.maps) {
      this.initMap()
    } else {
      const script = document.createElement('script')
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap)
      script.src = '//dapi.kakao.com/v2/maps/sdk.js?appkey=ab4ecf9e2a9676481a6d59d4445acf2b&autoload=false'
      document.head.appendChild(script)
    }
  },
  methods: {
    initMap () {
      var container = document.getElementById('map')
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      }

      var map = new kakao.maps.Map(container, options)
      map.setMapTypeId(kakao.maps.MapTypeId.HYBRID)
    }
  }
}
</script>

<style scoped>

</style>
