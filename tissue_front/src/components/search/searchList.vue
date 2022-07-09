<template>
    <v-container class="searchBox">
        <v-tabs color="blue lighten-3" centered height="70px" class="mb-10" v-model="active_tab">
            <v-tab v-for="item in items" :key="item.name" class="tab"
            @click=item.value>
                {{ item.name }} {{ item.count }}
            </v-tab>
        </v-tabs>
        <div v-if="whole">
            <div class="listSize" v-for="search in searchList" :key="search.performNo">
                <v-row justify="center">
                    <v-col cols="2">
                        <v-img :src="require(`../../assets/thumbNail/${search.performThumbnail}`)"
                        contain class="ml-6" width="100"
                        >
                        </v-img>
                    </v-col>
                    <v-col cols="6" class="mt-12">
                        <router-link :to="{name:'PerformanceDetailPage', params: { performNo: search.performNo.toString() }}"
                        style="color:black">
                        {{ search.performName }}
                        </router-link>
                    </v-col>
                    <v-divider vertical class="ml-7 mt-6 mr-7" style="height:100px"/>
                    <v-col cols="3" class="mt-12">
                        {{ search.performStart }} ~ {{ search.performEnd }}
                    </v-col>
                </v-row>
                <v-divider class="mt-3 mb-3"/>
            </div>
        </div>
        <div v-else>
            <div class="listSize" v-for="search in searchList2" :key="search.performNo">
                <v-row justify="center">
                    <v-col cols="2">
                        <v-img :src="require(`../../assets/thumbNail/${search.performThumbnail}`)"
                        contain class="ml-6" width="100"
                        >
                        </v-img>
                    </v-col>
                    <v-col cols="6" class="mt-12">
                        <router-link :to="{name:'PerformanceDetailPage', params: { performNo: search.performNo.toString() }}"
                        style="color:black">
                        {{ search.performName }}
                        </router-link>
                    </v-col>
                    <v-divider vertical class="ml-7 mt-6 mr-7" style="height:100px"/>
                    <v-col cols="3" class="mt-12">
                        {{ search.performStart }} ~ {{ search.performEnd }}
                    </v-col>
                </v-row>
                <v-divider class="mt-3 mb-3"/>
            </div>
        </div>
        <div class="noticeBox mt-15">
            <span class="ntitle"> 공지사항 </span>
            <hr class="mt-5 mb-5" color="skyblue">
            <v-data-table
            :headers="headers"
            :items="noticeSearchList"
            :page.sync="page"
            :items-per-page="7"
            @page-count="pageCount = $event"
            hide-default-footer>

            <template v-slot:[`item.noticeTitle`]="{ item }">
                <router-link
                    style="color: black"
                    :to="{ name: 'NoticeReadPage', params: { noticeNo: item.noticeNo } }">
                    {{ item.noticeTitle }}
                </router-link>
            </template>
            </v-data-table>
            <v-pagination
            class="mt-10"
            v-model="page"
            :length="pageCount"
            total-visible="5"
            color="pink lighten-3"
            circle>
        </v-pagination>
        </div>
    </v-container>
</template>

<script>
export default {
    name:'searchList',
    props: {
        searchList: {
            type: Array,
            required: true,
        },
        searchList2: {
            type: Array,
            required: true
        },
        noticeSearchList: {
            type: Array,
            required: true
        },
    },
    watch: {
        searchList () {
            this.active_tab = 0
            this.whole = true
        }
    },
    data () {
        return {
            items :[
                {name : '통합검색', value: () => { this.fetchOrigin() }},
                {name : '콘서트', value: () => { this.fetchConcert()}},
                {name : '뮤지컬', value: () => { this.fetchMusical()}},
                {name : '연극', value: () => { this.fetchTheater()}},
                {name : '전시회', value: () => { this.fetchExhibition()}}
            ],
            headers :[
                {text : '카테고리', value:'noticeCategory', width:100},
                {text : '제목', value:'noticeTitle', width:300},
                {text : '날짜', value:'noticeDate', width:100}
            ],
            countC: '',
            whole: true,
            active_tab:0,
            page: 1,
            pageCount: 0,
        }
    },
    created() {
        this.copy=this.searchList
        console.log(this.copy)
    },
    methods: {
        fetchOrigin() {
            this.whole = true
        },
        fetchConcert() {
            this.whole = false
            let origin = []
            for (let i = 0; i<this.searchList.length; i++) {
                if (this.searchList[i].performCategory == '콘서트') {
                    origin.push(this.searchList[i])
                    console.log(this.searchList[i])
                }
            }
            this.$emit('update:searchList2', origin)
        },
        fetchMusical() {
            this.whole = false
            let origin = []
            for (let i = 0; i<this.searchList.length; i++) {
                if (this.searchList[i].performCategory == '뮤지컬') {
                    origin.push(this.searchList[i])
                }
            }
            this.$emit('update:searchList2', origin)
        },
        fetchTheater() {
            this.whole = false
            let origin = []
            for (let i = 0; i<this.searchList.length; i++) {
                if (this.searchList[i].performCategory == '연극') {
                    origin.push(this.searchList[i])
                }
            }
            this.$emit('update:searchList2', origin)
        },
        fetchExhibition() {
            this.whole = false
            let origin = []
            for (let i = 0; i<this.searchList.length; i++) {
                if (this.searchList[i].performCategory == '전시회') {
                    origin.push(this.searchList[i])
                }
            }
            this.$emit('update:searchList2', origin)
        }
    }
}
</script>

<style scoped>
.tab{
    width:200px;
}
.listSize {
    width:1000px;
    margin: auto;
}
.searchBox {
    justify-content: center;
    align-items: center;
}
.noticeBox {
    width:1000px;
    margin: auto;
}
.ntitle {
    font-size: 25pt;
    color:skyblue;
}
</style>