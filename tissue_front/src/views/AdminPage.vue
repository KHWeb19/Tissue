<template>
    <div>
        <v-app>
            <v-card height="350px">
            <v-navigation-drawer absolute permanent>
                <template v-slot:prepend>
                    <v-list-item two-line>
                        <v-list-item-content>
                            <v-list-item-title>Tissue</v-list-item-title>
                            <v-list-item-subtitle>Logged In</v-list-item-subtitle>
                         </v-list-item-content>
                    </v-list-item>
                </template>
        
                <v-divider></v-divider>
        
                <v-list dense>
                <v-list-item v-for="item in items" :key="item.title" router :to="item.route">
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>
        
                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                </v-list>
            </v-navigation-drawer>
            </v-card>
        </v-app>
    </div>
</template>

<script>
export default {
    data() {
        return {
            items: [
                { title: 'Home', icon: 'mdi-home-city', route: '/'},
                { title: 'Performance', icon: 'mdi-account-group-outline', route: '/performanceRegisterPage'},
      ],
        }
    }
}
</script>