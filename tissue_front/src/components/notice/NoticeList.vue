<template>
    <div>
        <v-container>
            <h1>공지사항</h1>
                <div id = 'btn'>
                    <v-btn class='white--text' id = 'btnRegisterPage'
                    :to="{ name: 'NoticeRegisterPage' }">공지사항 등록</v-btn>
                </div>
                <input class="inputSearch" v-model="keyword" type="text"
                    placeholder="궁금하신 내용을 검색하세요." append-icon="mdi-magnify"><br><br>
                <v-data-table
                    :headers="headers"
                    :items="notices"
                    :search="keyword"
                    hide-default-footer
                    :page.sync="page"
                    :items-per-page="itemsPerPage"
                    @page-count="pageCount = $event"
                    >
                    <template v-slot:[`item.noticeTitle`]="{ item }">
                   <router-link style="color: black" :to="{ name: 'NoticeReadPage',
                                        params: { noticeNo: item.noticeNo } }">
                    {{ item.noticeTitle }}
                    </router-link>
                </template>
                </v-data-table>
                  <v-pagination
                          v-model="page"
                          :length="pageCount"></v-pagination>
        </v-container>
    </div>

</template>

<script>
export default {
  name: "NoticeList",
  props: {
    notices: {
      type: Array
    }
  },
  data () {
    return {
      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
      headers: [
        { text: '필독', value: 'noticeNecessary', width: '10%' },
        { text: '구분', value: 'noticeCategory', width: '15%' },
        { text: '제목', value: 'noticeTitle', width: '40%' },
        { text: '등록일', value: 'noticeDate', width: '20%' },
        { text: '조회수', value: 'noticeView', width: '10%' }
      ],
      keyword: ''
    }
  }
}
</script>


<style scoped>
#btn { text-align: right; font-size: 18px;}
#btnRegisterPage { background:blue;}
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
input:focus { outline-color: pink; }
</style>