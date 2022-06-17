<template>
    <div>
        <v-container><br><br><br><br>
            <h1>공지사항</h1><br>
                <v-input style="color: skyblue">{{ notice.noticeCategory }}</v-input>
                <v-input id="title" style="font-size: 26px">{{notice.noticeTitle}}</v-input>
                    <hr aria-setsize="5px">
                        <div id='a'>
                            <li style="color:gray">
                                등록일: {{notice.noticeDate}}&emsp;조회수: {{notice.noticeView}}
                            </li>
                            <router-link style="color: gray" :to="{ name: 'NoticeModifyPage', params: { noticeNo: notice.noticeNo } }">
                            <v-icon right style="color: gray">
                                mdi-pencil
                            </v-icon>수정
                            </router-link>
                            <a @click="onDelete(notice.noticeNo)">
                                <v-icon right style="color: red">
                                    mdi-delete
                                </v-icon>
                            </a>
                        </div><br>
                            <img v-if="this.notice.noticeImg !== null && this.notice.noticeImg !== 'null'"
                                :src="require(`@/assets/uploadImg/${this.notice.noticeImg}`)"
                                 width="90%">
                            <textarea rows="20" :value="notice.noticeContent" type="text" readonly></textarea><br>

                            <section v-if="this.notice.noticeYoutube !== null && this.notice.noticeYoutube !== ''">
                                <iframe width="1000" height="600" :src="(`https://www.youtube.com/embed/${notice.noticeYoutube}`)"
                                    title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                            </section>
                            <section v-else></section><br><br>
                            <hr aria-setsize="5px"><br>
                                <p>
                                    <v-icon right style="color: pink">
                                        mdi-alert-circle
                                    </v-icon>
                                    티켓오픈 일정은 Tissue 또는 기획사의 사정에 의해 사전예고 없이 변경 또는 취소 될 수 있습니다.
                                </p><br>
                            <div id ='btn'>
                                <v-btn id="btnListPage" :to="{ name: 'NoticeListPage' }">목록보기</v-btn><br><br>
                            </div>
            </v-container>
        </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "NoticeRead",
  props: {
    notice: {
      type: Object,
      required: true
    }
  },
  methods: {
    onDelete (noticeNo) {
      axios.delete(`notice/delete/${noticeNo}`, { noticeNo })
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


<style scoped>
textarea { width: 90%;
			padding: 10px;
			box-sizing: border-box;
            outline-style: none;
			border-radius: 5px;}
img { object-fit: contain; }
#a { text-align: right; font-size: 15px;}
#btn { text-align: center;}
#btnListPage { background:white; width: 250px; height: 55px; font-size: 18px;}
p { text-align: right; font-size: 14px; color: silver;}
</style>