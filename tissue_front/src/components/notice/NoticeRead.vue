<template>
    <div>
        <v-container>
            <h1>공지사항 읽기test</h1><br>
                <hr><br>
                    <v-input id="title">{{notice.noticeTitle}}</v-input>
                <hr aria-setsize="5px"><br>
                <div id='a'>
                    <li>
                        등록일: {{notice.noticeDate}} &nbsp;조회수: {{notice.noticeView}}
                    </li>
                </div>
                <hr aria-setsize="5px">
                <v-textarea :value="notice.noticeContent" label="내용" type="text" height="300px" readonly></v-textarea><br>
                <img v-if="this.notice.noticeImg !== null && this.notice.noticeImg !== 'null'"
                :src="require(`@/assets/uploadImg/${this.notice.noticeImg}`)">
                <section>
                    <iframe width="560" height="315" src="https://www.youtube.com/embed/d35cuUXEjYk?start=3" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                </section>
                <div id ='btn'>
                    <v-btn id="btnListPage"
                    :to="{ name: 'NoticeListPage' }">목록보기</v-btn>
                </div>


        </v-container>
    </div>

</template>

<script>
export default {
  name: "NoticeRead",
  props: {
    notice: {
      type: Object,
      required: true
    }
  }
}
</script>


<style scoped>
.title { font-size: 18px;}
#a { text-align: right; font-size: 15px;}
#btn { text-align: center; font-size: 20px;}
#btnListPage { background:white; width: 250px; height: 55px; }
</style>