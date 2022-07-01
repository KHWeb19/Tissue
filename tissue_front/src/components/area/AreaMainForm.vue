<template>
    <div align="center">
        <v-container>
            <h1>지역별 공연</h1>

            <v-tabs class="areaBox mt-5" fixed-tabs background-color="transparent" dark height="80px">
                <v-tabs-slider color="pink lighten-3"></v-tabs-slider>
                <v-tab v-for="tab in tabs" :key="tab.index" @click="onClickTab(tab)" class="black--text">
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
                            <div >
                                <v-row justify="center" class="categoryList" >
                                    <v-checkbox v-model="checked" value="콘서트" label="콘서트"></v-checkbox>
                                    <v-checkbox v-model="checked" value="뮤지컬" label="뮤지컬"></v-checkbox>
                                    <v-checkbox v-model="checked" value="연극" label="연극"></v-checkbox>
                                    <v-checkbox v-model="checked" value="전시회" label="전시회"></v-checkbox>    
                                </v-row>
                            </div>
                        </div>
                        <v-btn @click="applyFilter" class="mr-3" color="blue lighten-3">적용</v-btn>
                        <v-btn @click="resetFilter" color="transparent">초기화</v-btn>
                    </v-container>
                </v-card-actions>
            </v-card>


            <div class="countBox">
                 현재 예매가능한 공연은 <b style="color: skyblue">{{ copyPerformList.length }}개</b>입니다.
            </div>

            <div v-show="selectedTab === tabs[0]">
                <v-row>
                    <v-col v-for="perform in copyPerformList" :key="perform.performNo" lg="3" sm="6">
                        <v-card class="mx-auto" max-width="216" height="410" flat>
                        <v-img :src="require(`../../assets/thumbNail/${perform.performThumbnail}`)" height="300px"></v-img>
                        <v-card-title class="performTitle mb-1">
                            {{ perform.performName }}
                        </v-card-title>
                        <v-card-subtitle class="performSub pb-0">
                            {{ perform.performStart }} ~
                            {{ perform.performEnd }}</v-card-subtitle>
                        <div v-for="map in mapList" :key="map.mapNo">
                            <div v-if="perform.performNo == map.performNo">
                                <v-card-subtitle  class="performSub pt-0">
                                    {{ map.name }}
                                </v-card-subtitle>
                            </div>
                        </div>
                        </v-card>
                    </v-col>
                </v-row>
            </div>
            <div v-show="selectedTab === tabs[1]">
                 <v-row>
                    <v-col v-for="perform in copyPerformList" :key="perform.performNo" lg="3" sm="6">
                        <div v-if="tabs[1].name == perform.performArea">
                            <v-card class="mx-auto" max-width="216" height="410" flat>
                                <v-img :src="require(`../../assets/thumbNail/${perform.performThumbnail}`)" height="300px"></v-img>
                                <v-card-title class="performTitle mb-1">
                                    {{ perform.performName }}
                                </v-card-title>
                                <v-card-subtitle class="performSub pb-0">
                                    {{ perform.performStart }} ~
                                    {{ perform.performEnd }}</v-card-subtitle>
                                <div v-for="map in mapList" :key="map.mapNo">
                                    <div v-if="perform.performNo == map.performNo">
                                        <v-card-subtitle  class="performSub pt-0">
                                            {{ map.name }}
                                        </v-card-subtitle>
                                    </div>
                                </div>
                            </v-card>
                        </div>
                    </v-col>
                </v-row>
            </div>
             <div v-show="selectedTab === tabs[2]">
                 <v-row>
                    <v-col v-for="perform in this.copyPerformList" :key="perform.performNo" lg="3" sm="6">
                        <div v-if="tabs[2].name == perform.performArea">
                            <v-card class="mx-auto" max-width="216" height="410" flat>
                                <v-img :src="require(`../../assets/thumbNail/${perform.performThumbnail}`)" height="300px"></v-img>
                                <v-card-title class="performTitle mb-1">
                                    {{ perform.performName }}
                                </v-card-title>
                                <v-card-subtitle class="performSub pb-0">
                                    {{ perform.performStart }} ~
                                    {{ perform.performEnd }}</v-card-subtitle>
                                <div v-for="map in mapList" :key="map.mapNo">
                                    <div v-if="perform.performNo == map.performNo">
                                        <v-card-subtitle  class="performSub pt-0">
                                            {{ map.name }}
                                        </v-card-subtitle>
                                    </div>
                                </div>
                            </v-card>
                        </div>
                    </v-col>
                </v-row>
            </div>
            <div v-show="selectedTab === tabs[3]">
                 <v-row>
                    <v-col v-for="perform in copyPerformList" :key="perform.performNo" lg="3" sm="6">
                        <div v-if="tabs[3].name == perform.performArea">
                            <v-card class="mx-auto" max-width="216" height="410" flat>
                                <v-img :src="require(`../../assets/thumbNail/${perform.performThumbnail}`)" height="300px"></v-img>
                                <v-card-title class="performTitle mb-1">
                                    {{ perform.performName }}
                                </v-card-title>
                                <v-card-subtitle class="performSub pb-0">
                                    {{ perform.performStart }} ~
                                    {{ perform.performEnd }}</v-card-subtitle>
                                <div v-for="map in mapList" :key="map.mapNo">
                                    <div v-if="perform.performNo == map.performNo">
                                        <v-card-subtitle  class="performSub pt-0">
                                            {{ map.name }}
                                        </v-card-subtitle>
                                    </div>
                                </div>
                            </v-card>
                        </div>
                    </v-col>
                </v-row>
            </div>
            <div v-show="selectedTab === tabs[4]">
                 <v-row>
                    <v-col v-for="perform in copyPerformList" :key="perform.performNo" lg="3" sm="6">
                        <div v-if="tabs[4].name == perform.performArea">
                            <v-card class="mx-auto" max-width="216" height="410" flat>
                                <v-img :src="require(`../../assets/thumbNail/${perform.performThumbnail}`)" height="300px"></v-img>
                                <v-card-title class="performTitle mb-1">
                                    {{ perform.performName }}
                                </v-card-title>
                                <v-card-subtitle class="performSub pb-0">
                                    {{ perform.performStart }} ~
                                    {{ perform.performEnd }}</v-card-subtitle>
                                <div v-for="map in mapList" :key="map.mapNo">
                                    <div v-if="perform.performNo == map.performNo">
                                        <v-card-subtitle  class="performSub pt-0">
                                            {{ map.name }}
                                        </v-card-subtitle>
                                    </div>
                                </div>
                            </v-card>
                        </div>
                    </v-col>
                </v-row>
            </div>
            <div v-show="selectedTab === tabs[5]">
                 <v-row>
                    <v-col v-for="perform in copyPerformList" :key="perform.performNo" lg="3" sm="6">
                        <div v-if="tabs[5].name == perform.performArea">
                            <v-card class="mx-auto" max-width="216" height="410" flat>
                                <v-img :src="require(`../../assets/thumbNail/${perform.performThumbnail}`)" height="300px"></v-img>
                                <v-card-title class="performTitle mb-1">
                                    {{ perform.performName }}
                                </v-card-title>
                                <v-card-subtitle class="performSub pb-0">
                                    {{ perform.performStart }} ~
                                    {{ perform.performEnd }}</v-card-subtitle>
                                <div v-for="map in mapList" :key="map.mapNo">
                                    <div v-if="perform.performNo == map.performNo">
                                        <v-card-subtitle  class="performSub pt-0">
                                            {{ map.name }}
                                        </v-card-subtitle>
                                    </div>
                                </div>
                            </v-card>
                        </div>
                    </v-col>
                </v-row>
            </div>
            <div v-show="selectedTab === tabs[6]">
                 <v-row>
                    <v-col v-for="perform in copyPerformList" :key="perform.performNo" lg="3" sm="6">
                        <div v-if="tabs[6].name == perform.performArea">
                            <v-card class="mx-auto" max-width="216" height="410" flat>
                                <v-img :src="require(`../../assets/thumbNail/${perform.performThumbnail}`)" height="300px"></v-img>
                                <v-card-title class="performTitle mb-1">
                                    {{ perform.performName }}
                                </v-card-title>
                                <v-card-subtitle class="performSub pb-0">
                                    {{ perform.performStart }} ~
                                    {{ perform.performEnd }}</v-card-subtitle>
                                <div v-for="map in mapList" :key="map.mapNo">
                                    <div v-if="perform.performNo == map.performNo">
                                        <v-card-subtitle  class="performSub pt-0">
                                            {{ map.name }}
                                        </v-card-subtitle>
                                    </div>
                                </div>
                            </v-card>
                        </div>
                    </v-col>
                </v-row>
            </div>
        </v-container>
    </div>
</template>

<script>
export default {
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
            copyPerformList2: this.performances,
            selectedTab: '', 
            tabs: [{name:'전체'}, {name:'서울'}, {name:'경기/인천'}, {name:'대전/충청/강원'}, 
                {name:'부산/대구/울산/경상'}, {name:'광주/전라'}, {name:'제주'}], 
            detailSearch: false,
            checked: '',
        }
    },
    methods: {
        onClickTab(tab) {
            this.selectedTab = tab
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

           
        
        /* v-model="checked" 배열로 선언했을 때 --> 첫번째 인덱스의 결과만 나옴
           console.log(this.checked)

           for(let i=0; i < this.checked.length; i++) {
                this.copyPerformList = this.copyPerformList.filter(e => {
                    return e.performCategory.match(this.checked[i])
                })

                return this.copyPerformList
           } */

        },
        resetFilter() {
            this.checked = ''
            this.copyPerformList2 = [...this.originalPerformList]
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
  font-size: 20px;
  justify-content: center;
  color: black;
}
card-subtitle {
  font-weight: 100;
  font-size: 15px;
  text-align: center;
  color: #BDBDBD
}
.countBox {
  margin: 50px;
  text-align: center;
  font-size: 20px;
}
.categoryBox{
    background-color: white;
    height: 130px;
    width: 100%;
    margin-top: 30px;
    margin-bottom: 30px;
}
.detailSearchBox{
    background-color: rgb(241, 241, 241) ;
}
.detailSearchTxt{
    margin-left: 5px;
    font-size: 15px;
    font-weight: 100;
}
</style>