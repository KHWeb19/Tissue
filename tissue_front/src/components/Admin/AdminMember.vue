<template>
    <v-container>
        <v-app-bar app elevation="3">
            <v-toolbar-title class="ml-3">
                📌 회원 관리
            </v-toolbar-title>
        </v-app-bar>
        <v-container>
            <v-row class="countMember ml-3 mt-10">
                전체 회원 수&nbsp;<span style="color:skyblue">{{ member.length }}</span> 명
                </v-row>
                <v-row justify="end">
                    <v-col cols="5">
                    <v-text-field
                        v-model="keyword"
                        append-icon="search"
                        label="회원 검색"
                        single-line
                        color="pink lighten-3"
                    ></v-text-field>
                </v-col>
            </v-row>
            <v-data-table
                :headers="headers"
                :items="member"
                hide-default-footer
                :search="keyword"
                :page.sync="page"
                :items-per-page="itemsPerPage"
                @page-count="pageCount = $event"
                >
            <template v-slot:[`item.delete`]="{item}">
                <v-icon small @click="saveRole(item.memberNo, item.role)">mdi-content-save</v-icon>
                <v-icon small @click="deleteItem(item.memberId, item.memberNo)"> delete </v-icon>
            </template>
            <template v-slot:[`item.role`]="{item}">
                <select v-model="item.role">
                    <option value="USER">회원</option>
                    <option value="ADMIN">관리자</option>
                </select>
            </template>
            </v-data-table>
            <v-divider class="mb-5"/>
            <v-pagination
                    v-model="page"
                    :length="pageCount"
                    total-visible="10"
                    color="pink lighten-3"
                    circle
                >
            </v-pagination>
        </v-container>
    </v-container>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
export default {
    name:'AdminMember',
    data() {
        return {
            page: 1,
            pageCount: 0,
            itemsPerPage: 10,
            headers :[
                {text: '회원번호', value: 'memberNo'},
                {text: '아이디', value:'memberId'},
                {text: '휴대폰', value:'memberPhone'},
                {text: '회원등급', value:'memberGrade'},
                {text: '가입일', value: 'memberRegDate'},
                {text: '회원권한', value: 'role'},
                {text: 'Action', value: 'delete', width:'8%'}
            ],
            keyword:''
        }
    },
    computed: {
        ...mapState(['member'])
    },
    mounted() {
        this.fetchMember()
    },
    methods: {
        ...mapActions(['fetchMember']),

        deleteItem(memberId, memberNo){
            let result = confirm (memberId +'님을 강제 탈퇴시키겠습니까?')
            if(result) {
                axios.delete(`Admin/${memberNo}`, { memberNo })
                .then(() => {
                    alert('탈퇴시켰습니다.')
                    history.go(0)
                })
                .catch(() => {
                    console.log(memberNo)
                    alert('회원 삭제 실패')
                })
            }
        },
        saveRole(memberNo, role){
            axios.put('Admin/memberInfo/modify', {memberNo, role})
            .then(() => {
                alert("권한이 수정되었습니다.")
            })
            .catch(() => {
                alert("오류")
                console.log(memberNo, role)
            })
        }
    },
}
</script>

<style scoped>
.countMember{
    font-size:18pt;
}
select {
    width:80px;
    padding: .5em;
    text-align: center;
    border: 2px solid skyblue;
    border-radius: 20px;
    -webkit-appearance: none;
    appearance: none;
    -moz-appearance: none;
}
select:hover {
  border-color: pink;
}

select:focus {
  border-color: pink;
  outline: none;
}

</style>