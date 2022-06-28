<template>
    <v-container class="searchBox">
        <v-tabs color="blue lighten-3" centered height="70px" class="mb-10">
            <v-tab v-for="item in items" :key="item.name" class="tab"
            @click=item.value>
                {{ item.name }} {{item.value}}
            </v-tab>
            <v-tab-item class="mt-10">
                <div class="listSize" v-for="search in searchList" :key="search.performNo">
                    <v-row justify="center">
                        <v-col cols="2">
                            <v-img :src="require(`../../assets/thumbNail/${search.performThumbnail}`)"
                            contain class="ml-6" width="100"
                            >
                            </v-img>
                        </v-col>
                        <v-col cols="6" class="mt-12">
                            <router-link :to="{name:'PerformanceDetailPage', params: { performNo: search.perfromNo }}"
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
            </v-tab-item>
            <v-tab-item class="mt-10">
                <div class="listSize" v-for="search in searchList" :key="search.performNo">
                    <div v-if="search.performCategory=='콘서트'">
                    <v-row justify="center">
                        <v-col cols="2">
                            <v-img :src="require(`../../assets/thumbNail/${search.performThumbnail}`)"
                            contain class="ml-6" width="100"
                            >
                            </v-img>
                        </v-col>
                        <v-col cols="6" class="mt-12">
                            <router-link :to="{name:'PerformanceDetailPage', params: { performNo: search.perfromNo }}"
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
            </v-tab-item>
            <v-tab-item class="mt-10">
                <div class="listSize" v-for="search in searchList" :key="search.performNo">
                    <div v-if="search.performCategory=='뮤지컬'">
                    <v-row justify="center">
                        <v-col cols="2">
                            <v-img :src="require(`../../assets/thumbNail/${search.performThumbnail}`)"
                            contain class="ml-6" width="100"
                            >
                            </v-img>
                        </v-col>
                        <v-col cols="6" class="mt-12">
                            <router-link :to="{name:'PerformanceDetailPage', params: { performNo: search.perfromNo }}"
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
            </v-tab-item>
            <v-tab-item class="mt-10">
                <div class="listSize" v-for="search in searchList" :key="search.performNo">
                    <div v-if="search.performCategory=='연극'">
                    <v-row justify="center">
                        <v-col cols="2">
                            <v-img :src="require(`../../assets/thumbNail/${search.performThumbnail}`)"
                            contain class="ml-6" width="100"
                            >
                            </v-img>
                        </v-col>
                        <v-col cols="6" class="mt-12">
                            <router-link :to="{name:'PerformanceDetailPage', params: { performNo: search.perfromNo }}"
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
            </v-tab-item>
            <v-tab-item class="mt-10">
                <div class="listSize" v-for="search in searchList" :key="search.performNo">
                    <div v-if="search.performCategory=='전시회'">
                    <v-row justify="center">
                        <v-col cols="2">
                            <v-img :src="require(`../../assets/thumbNail/${search.performThumbnail}`)"
                            contain class="ml-6" width="100"
                            >
                            </v-img>
                        </v-col>
                        <v-col cols="6" class="mt-12">
                            <router-link :to="{name:'PerformanceDetailPage', params: { performNo: search.perfromNo }}"
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
            </v-tab-item>
        </v-tabs>
        <div class="noticeBox mt-15">
            <span class="ntitle"> 공지사항 </span>
            <hr class="mt-5 mb-5" color="skyblue">
            <v-data-table
            :headers="headers"
            :items="noticeSearchList">
            </v-data-table>
        </div>
    </v-container>
</template>

<script>
export default {
    name:'searchList',
    props: {
        searchList: {
            type: Array,
            required: true
        },
        noticeSearchList: {
            type: Array,
            required: true
        }
    },
    data () {
        return {
            items :[
                {name : '통합검색', value: this.searchList.length},
                {name : '콘서트', },
                {name : '뮤지컬', },
                {name : '연극', },
                {name : '전시회',}
            ],
            headers :[
                {text : '카테고리', value:'noticeCategory', width:100},
                {text : '제목', value:'noticeTitle'},
                {text : '날짜', value:'noticeDate', width:100}
            ]
        }
    },
    methods: {
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