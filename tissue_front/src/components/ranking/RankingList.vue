<template>
    <v-form @submit.prevent="onSubmit">
        <v-date-picker v-model="bookDate"/>
        <v-btn type="submit"/>
    </v-form>
</template>

<script>
export default {
  name: 'RankingList',
  data () {
    return {
      bookDate: ''
    }
  },
  methods: {
    onSubmit () {
      const { bookDate } = this
      console.log(bookDate)
      this.$emit('submit', {bookDate})
    }
  }
}
</script>
