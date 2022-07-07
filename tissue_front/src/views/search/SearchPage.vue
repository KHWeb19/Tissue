<template>
<div>
    <v-container class="search">
        <div class="keywordBox">
            <v-row justify="center">
                <div class="keyword">
                    <span style="color:skyblue">'{{ keyword }}' </span> 에 대한 검색 결과입니다.
                </div>
            </v-row>
            <v-row justify="center">
                <v-text-field class="mt-5 shrink" color='pink lighten-3'
                append-icon="mdi-magnify"
                @click:append="search"
                v-model="keyword2"
                ></v-text-field>
            </v-row>
        </div>
    </v-container>
    <v-container class="mt-7 mb-10">
        <div>
            <v-row justify="center">
                <search-list :searchList="searchList" 
                :searchList2="searchList2"
                :noticeSearchList="noticeSearchList" 
                @update:searchList2="searchList2 = $event"/>
            </v-row>
        </div>
    </v-container>
    <v-container class="footer" fluid>
        <v-row justify="center" class="mb-5"> 
            <span style="zoom:1.2">😥 &nbsp; </span> 
            <span class="mt-1"> 앗, 찾으시는 내용이 없으신가요? </span>
        </v-row>
        <v-row justify="center">
            <span class="mt-2">고객센터 바로가기</span>
            <v-icon color="pink lighten-3" x-large class="arrow">mdi-arrow-down-thick</v-icon> 
        </v-row>
    </v-container>
</div>
</template>

<script>
import searchList from '@/components/search/searchList.vue'
import { mapActions, mapMutations, mapState } from 'vuex'
export default {
  components: { searchList },
    name:'SearchPage',
    props: {
        keyword: {
            type: String,
            required: true
        }
    },
    data () {
        return {
            keyword2:'',
        }
    },
    computed: {
        searchList2: {
            ...mapState({get:'searchList2'}),
            ...mapMutations({set:'FETCH_SEARCH_LIST2'})
        },
        ...mapState(['searchList', 'noticeSearchList']),

    },
    created () {
        this.fetchSearchList(this.keyword),
        this.fetchNoticeSearchList(this.keyword)
    },
    methods: {
        ...mapActions(['fetchSearchList','fetchNoticeSearchList']),

        async search() {
            this.fetchSearchList(this.keyword2),
            this.fetchNoticeSearchList(this.keyword2)
            this.$router.push({name: 'SearchPage', params: { keyword: this.keyword2 }})
        }
    }
}
</script>

<style scoped>
.keywordBox{
    background-color: rgb(241, 241, 241) ;
    width:1200px;
    height: 210px;
    padding: 65px;
}
.keyword{
    margin: auto;
    padding: auto;
    font-size:20pt;
}
.search {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top:5%;
}
.v-text-field{
    width: 400px;
}
.footer {
    background-color:rgb(241, 241, 241) ;
    padding-top:3%;
    padding-bottom:2%;
}
.arrow {
    animation: arrows 0.5s linear 0s infinite alternate;
    top: 0px;
}
@keyframes arrows {
    0% {
        top: 0px;
    }
    100% {
        top: 15px;
    }
}
</style>