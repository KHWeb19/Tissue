<template>
    <div>
        <v-container>
            <h1>공연 리스트 페이지 TEST</h1>
                <div id = 'btn'>
                    <v-btn class='white--text' id = 'btnRegisterPage'
                    :to="{ name: 'PerformanceRegisterPage' }">글등록</v-btn>
                </div>
                <input class="inputSearch" v-model="keyword" type="text" placeholder="공연 정보를 검색하세요."><br><br>
                <v-data-table
                    :headers="headers"
                    :items="performance"
                    :search="keyword"
                    >
                </v-data-table>
        </v-container>
    </div>

</template>

<script>
export default {
  name: "PerformanceList",
  props: {
    performance: {
      type: Array
    }
  },
  data () {
    return {
      headers: [
        { text: '대표이미지', value: 'performThumbnail', width: '90px' },
        { text: '공연명', value: 'performName', width: '90px' },
        { text: '공연기간', value: 'performStart', width: '230px' },
        { text: '공연장소', value: 'performArea', width: '20px' }
      ],
      keyword: ''
    }
  }
}
</script>


<style scoped>
#btn { text-align: right; font-size: 18px;}
#btnRegisterPage { background:orange;}
.search {
    position: relative;
    text-align: center;
    margin: 0 auto;
}
.inputSearch {
    width: 80%;
    height: 45px;
    border-radius: 20px;
    border: 1px solid #bbb;
    margin: 10px 0;
    padding: 10px 12px;
}
input:focus { outline-color: orange; }
</style>