<template>
    <div align="center">
        <area-banner/>
        <v-container>
            <span class="aTitle">지역별 공연</span>

            <v-tabs class="areaBox mt-10" fixed-tabs background-color="transparent" dark height="80px">
                <v-tabs-slider color="pink lighten-3"></v-tabs-slider>
                <v-tab v-for="tab in tabs" :key="tab.name" @click=tab.value class="black--text">
                    {{ tab.name }}
                </v-tab>
            </v-tabs>

            <br><br>

            <v-card style="width: 100%" class="detailSearchBox" flat>
                <v-row>
                    <v-card-title class="detailSearchTxt">
                        상세검색
                        <v-icon v-if="!detailSearch" @click="detailSearch = true">mdi-plus</v-icon>
                        <v-icon v-else @click="detailSearch = false">mdi-minus</v-icon>
                    </v-card-title>
                    <v-spacer/>
                    <v-card-title>
                        <v-tabs color="blue lighten-3" background-color="transparent">
                            <v-tabs-slider color="pink lighten-3"></v-tabs-slider>
                            <v-tab class="sortbtn black--text" @click="sortRecent">최신순</v-tab>
                            <v-tab class="sortbtn black--text" @click="sortEnd">종료임박순</v-tab>
                        </v-tabs>
                    </v-card-title>
                </v-row>
                <v-card-actions v-if="detailSearch">
                    <v-container class="pt-0" fluid>
                        <div class="categoryBox" >
                            <div class="category-select">
                                <v-row justify="center" class="categoryList">
                                    <v-checkbox class="checkBox"  v-model="checked" value="콘서트" label="콘서트"></v-checkbox>
                                    <v-checkbox class="checkBox" v-model="checked" value="뮤지컬" label="뮤지컬"></v-checkbox>
                                    <v-checkbox class="checkBox" v-model="checked" value="연극" label="연극"></v-checkbox>
                                    <v-checkbox class="checkBox" v-model="checked" value="전시회" label="전시회"></v-checkbox>
                                </v-row>
                            </div>
                        </div>
                        <v-btn @click="applyFilter" class="mr-3" color="blue lighten-3">적용</v-btn>
                        <v-btn @click="fetchAll" color="transparent">초기화</v-btn>
                    </v-container>
                </v-card-actions>
            </v-card>

            <div class="countBox">
                 현재 예매가능한 공연은 <b style="color: skyblue">{{ copyPerformList.length }}개</b>입니다.
            </div>

            <v-row>
                <v-col v-for="perform in this.copyPerformList" :key="perform.performNo" lg="3" sm="6">
                        <v-card class="mx-auto" max-width="216" flat>
                            <v-img :src="require(`../../assets/thumbNail/${perform.performThumbnail}`)" height="300px"></v-img>
                            <div class="performTitle mt-5">

                                {{ perform.performName }}
                            </div>

                            <v-card-subtitle class="performSub pb-0">
                                {{ perform.performStart }} ~
                                {{ perform.performEnd }}</v-card-subtitle
                            >
                            <v-card-subtitle class="performSub pt-0">
                                {{ perform.performArea }}</v-card-subtitle
                            >
                            <div v-for="map in mapList" :key="map.mapNo">
                                <div v-if="perform.performNo == map.performNo">
                                    <v-card-subtitle  class="performSub pt-0">
                                        {{ map.name }}
                                    </v-card-subtitle>
                            </div>
                            </div>
                        </v-card>
                    </router-link>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
import AreaBanner from './AreaBanner.vue'
export default {
  components: { AreaBanner },
    name: 'AreaMainForm',
    props: {
        performances: {
            type: Array,
            required: true,
        },
        mapList: {
            type: Array,
            required: true,
        }
    },
    data() {
        return {
            originalPerformList: [...this.performances],
            copyPerformList: this.performances,
            selectedTab: '', 
            tabs: [
                    {name:'전체', value: () => { this.fetchAll() }}, 
                    {name:'서울', value: () => { this.fetchSeoul() }}, 
                    {name:'경기/인천', value: () => { this.fetchIncheon() }}, 
                    {name:'대전/충청/강원', value: () => { this.fetchDaejeon() }}, 
                    {name:'부산/대구/울산/경상', value: () => { this.fetchBusan() }}, 
                    {name:'광주/전라', value: () => { this.fetchGwangju() }}, 
                    {name:'제주', value: () => { this.fetchJeju() }}
                ],
            detailSearch: false,
            checked: '',
        }
    },
    methods: {
        fetchAll() {
            this.selectedTab = this.tabs[0]
            this.checked = ''
            this.copyPerformList = [...this.originalPerformList]
        },
        fetchSeoul() {
            this.copyPerformList = [...this.originalPerformList]

            this.copyPerformList = this.copyPerformList.filter(e => {
                    return e.performArea.match('서울')
                })
        },
        fetchIncheon() {
            this.copyPerformList = [...this.originalPerformList]

            this.copyPerformList = this.copyPerformList.filter(e => {
                    return e.performArea.match('경기/인천')
                })
        },
        fetchDaejeon() {
            this.copyPerformList = [...this.originalPerformList]

            this.copyPerformList = this.copyPerformList.filter(e => {
                    return e.performArea.match('대전/충청/강원')
                })
        },
        fetchBusan() {
            this.copyPerformList = [...this.originalPerformList]

            this.copyPerformList = this.copyPerformList.filter(e => {
                        return e.performArea.match('부산/대구/울산/경상')
                    })
        },
        fetchGwangju() {
            this.copyPerformList = [...this.originalPerformList]

            this.copyPerformList = this.copyPerformList.filter(e => {
                    return e.performArea.match('광주/전라')
                })
        },
        fetchJeju() {
            this.copyPerformList = [...this.originalPerformList]

            this.copyPerformList = this.copyPerformList.filter(e => {
                    return e.performArea.match('제주')
                })
        },
        applyFilter() {
            if(this.checked == '콘서트') {
                this.copyPerformList = this.copyPerformList.filter(e => {
                    return e.performCategory.match(this.checked)
                })
                return this.copyPerformList
           }

            if(this.checked == '뮤지컬') {
                this.copyPerformList = this.copyPerformList.filter(e => {
                    return e.performCategory.match(this.checked)
                })
                return this.copyPerformList
           }

            if(this.checked == '연극') {
                this.copyPerformList = this.copyPerformList.filter(e => {
                    return e.performCategory.match(this.checked)
                })
                return this.copyPerformList
           }

            if(this.checked == '전시회') {
                this.copyPerformList = this.copyPerformList.filter(e => {
                    return e.performCategory.match(this.checked)
                })
                return this.copyPerformList
           }
        },
        sortRecent() {     
            this.copyPerformList.sort(function (a, b) {
                return new Date(a.performStart) - new Date(b.performStart)
            })
        },
        sortEnd() {
            this.copyPerformList.sort(function (a, b) {
                return new Date(a.performEnd) - new Date(b.performEnd)
            })
        },
    },
    created() {
        this.selectedTab = this.tabs[0]
    }
}
</script>

<style scoped>
/* .areaBox{
border: 2px solid;
border-collapse: separate;
border-color: #90CAF9;
font-size: 20px;
} */
.performTitle {
  font-size: 18px;
  text-align: center;
  justify-content: center;
  color: black;
  
}
.performSub {
  font-size: 13px;
  text-align: center;
  font-family: 'Nanum Gothic', sans-serif !important;
}
.performSub {
  font-family: 'Nanum Gothic', sans-serif !important;
  font-size: 13px;
  text-align: center;
}
.countBox {
  margin-top:80px;
  margin-bottom:80px;
  text-align: center;
  font-size: 20px;
}
.categoryBox{
    background-color: white;
    height: 130px;
    width: 100%;
    margin-top: 30px;
    margin-bottom: 30px;
    padding : 50px 0;
}
.category-select{
    /* font-family: 'Nanum Gothic', sans-serif !important; */
    font-size:30pt;
}
.checkBox {
    margin-right: 80px;
}
.detailSearchBox{
    background-color: rgb(241, 241, 241) ;
}
.detailSearchTxt{
    margin-left: 5px;
    font-size: 15px;
    font-weight: 100;
}
.aTitle {
    font-size:30pt;
}
</style>