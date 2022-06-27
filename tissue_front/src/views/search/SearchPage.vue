<template>
    <v-container class="search">
        <div class="keywordBox">
            <v-row justify="center">
                <div class="keyword">
                    <span style="color:skyblue">'{{ keyword }}' </span> 에 대한 검색 결과입니다.
                </div>
            </v-row>
            <v-row justify="center">
                <v-text-field color='pink lighten-3'
                append-icon="mdi-magnify"
                @click:append="search"
                :value="keyword"></v-text-field>
            </v-row>
        </div>
    </v-container>
</template>

<script>
export default {
    name:'SearchPage',
    props: {
        keyword: {
            type: String,
            required: true
        }
    },
    methods: {
        search() {
        
        }
    }
}
</script>

<style scoped>
.keywordBox{
    background-color: rgb(241, 241, 241) ;
    width:1000px;
    height: 210px;
    padding: 80px;
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
    margin-top:7%;
}
</style>