<template>
    <div align="center">
        <v-container>
            <h1>지역별 공연</h1>

            <!-- <ul class="tabs">
                <li v-for="tab in tabs" v-bind:class="{active : tab === selectedTab}" :key="tab.index" @click="onClickTab(tab)">
                    {{ tab.name }}
                </li>
            </ul> -->

            <v-tabs class="tabs mt-5" fixed-tabs background-color="blue lighten-3" dark>
                <v-tab v-for="tab in tabs" :key="tab" @click="onClickTab(tab)">
                    {{ tab.name }}
                </v-tab>
            </v-tabs>

            <br><br>

            <v-card style="width: 100%" class="detailSearchBox">
                <v-row>
                    <v-card-title class="detailSearchTxt">
                        상세검색
                        <v-icon v-if="!detailSearch">mdi-plus</v-icon>
                        <v-icon v-else>mdi-minus</v-icon>
                    </v-card-title>
                    <v-spacer/>
                    <v-card-title>
                        <v-tabs>
                            <v-tab @click="sortRecent">최신순</v-tab>
                            <v-tab @click="sortEnd">종료임박순</v-tab>
                        </v-tabs>
                    </v-card-title>
                </v-row>
                <v-container class="pt-0" fluid>
                    <v-row>
                        <v-card-subtitle>장르</v-card-subtitle>
                        <v-checkbox label="콘서트" hide-details dense></v-checkbox>
                        <v-checkbox label="뮤지컬" hide-details dense></v-checkbox>
                        <v-checkbox label="연극" hide-details dense></v-checkbox>
                        <v-checkbox label="전시회" hide-details dense></v-checkbox>
                    </v-row>
                </v-container>
            </v-card>


            <div class="countBox">
                현재 예매가능한 공연은
                <b style="color: skyblue">{{ performances.length }}개</b>
                입니다.
            </div>

            <div v-if="selectedTab === tabs[0]">
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
            <div v-if="selectedTab === tabs[1]">
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
             <div v-if="selectedTab === tabs[2]">
                 <v-row>
                    <v-col v-for="perform in copyPerformList" :key="perform.performNo" lg="3" sm="6">
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
            <div v-if="selectedTab === tabs[3]">
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
            <div v-if="selectedTab === tabs[4]">
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
            <div v-if="selectedTab === tabs[5]">
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
            <div v-if="selectedTab === tabs[6]">
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
            selectedTab: '', 
            tabs: [{name:'전체'}, {name:'서울'}, {name:'경기/인천'}, {name:'대전/충청/강원'}, 
                {name:'부산/대구/울산/경상'}, {name:'광주/전라'}, {name:'제주'}], 
            detailSearch: false,
        }
    },
    methods: {
        onClickTab(tab) {
            this.selectedTab = tab
        },
        sortRecent() {
            console.log(this.copyPerformList)
            this.copyPerformList.sort(function (a, b) {
                return new Date(a.performStart) - new Date(b.performStart)
            })
        },
        sortEnd() {
            this.copyPerformList.sort(function (a, b) {
                return new Date(a.performEnd) - new Date(b.performEnd)
            })
        }
    },
    created() {
        this.selectedTab = this.tabs[0]
    }
}
</script>

<style scoped>
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
.detailSearchTxt{
    font-size: 15px;
    font-weight: 100;
}
</style>