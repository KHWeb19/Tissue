<template>
    <div align="center">
        <v-container>
            <br><br><br><br>
            <h1>지역별 공연</h1>

            <!-- <v-tabs class="tabs mt-5" 
                v-bind:class="{active : tab === selectedTab}" 
                @click="onClickTab(tab)" 
                fixed-tabs background-color="blue lighten-3" dark>

                <v-tab>전체</v-tab>
                <v-tab>서울</v-tab>
                <v-tab>경기/인천</v-tab>
                <v-tab>대전/충청/강원</v-tab>
                <v-tab>부산/대구/울산/경상</v-tab>
                <v-tab>광주/전라</v-tab>
                <v-tab>제주</v-tab>
            </v-tabs> -->

            <ul class="tabs">
                <li v-for="tab in tabs" v-bind:class="{active : tab === selectedTab}" :key="tab.index" @click="onClickTab(tab)">
                    {{ tab.name }}
                </li>
            </ul>

            <!-- <v-tabs class="tabs mt-5" v-bind:class="{active : tab === selectedTab}" :key="tab.index" @click="onClickTab(tab)"
                fixed-tabs background-color="blue lighten-3" dark>
            </v-tabs> -->

            <br><br><br><br>
            <p>상세검색 구역</p>
            <br><br><br><br>
            <h3>현재 예매 가능한 공연은 총  {{performances.length}} 개 입니다.</h3>
            <br><br><br><br>

            <div v-if="selectedTab === tabs[0]">
                <v-row>
                    <v-col v-for="perform in performances" :key="perform.performNo" lg="3" sm="6">
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
                    <v-col v-for="perform in performances" :key="perform.performNo" lg="3" sm="6">
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
                    <v-col v-for="perform in performances" :key="perform.performNo" lg="3" sm="6">
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
                    <v-col v-for="perform in performances" :key="perform.performNo" lg="3" sm="6">
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
            selectedTab: '', 
            tabs: [{name:'전체'}, {name:'서울'}, {name:'경기/인천'}, {name:'대전/충청/강원'}, 
                {name:'부산/대구/울산/경상'}, {name:'광주/전라'}, {name:'제주'}], 
        }
    },
    methods: {
        onClickTab(tab) {
            this.selectedTab = tab
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
</style>