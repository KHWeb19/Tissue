<template>
    <div align="center">
        <notice-read v-if="notice" :notice="notice"/>
        <router-link style="color: blue" :to="{ name: 'NoticeModifyPage', params: { noticeNo } }">
            <b>게시물을 수정하시겠습니까?</b>&emsp;&emsp;&emsp;
        </router-link>
        <a style="color:blue" @click="onDelete(notice.noticeNo)">
          <v-icon right>
          mdi-delete
      </v-icon>
          <b>삭제</b></a>
    </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import NoticeRead from '@/components/notice/NoticeRead.vue'


export default {
  name: 'NoticeReadPage',
  components: {
      NoticeRead
  },
  props: {
      noticeNo: {
          type: String,
          required: true
      }
  },
  computed: {
      ...mapState(['notice'])
  },
  created () {
      this.fetchNotice(this.noticeNo)
        .catch(() => {
            alert('공지사항 요청 실패')
            this.$router.back()
        })
  },
  methods: {
      ...mapActions(['fetchNotice']),
      onDelete (noticeNo) {
      axios.delete(`http://localhost:7777/notice/delete/${noticeNo}`, { noticeNo })
        .then(() => {
          alert('게시물 삭제 성공!!')
          this.$router.replace({
            name: 'NoticeListPage'
          })
        })
        .catch(() => {
          alert('게시물 삭제 실패!')
        })
    }
  }
}
</script>

