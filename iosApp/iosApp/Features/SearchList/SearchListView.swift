//
//  SearchListView.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 22.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import LivesportKit

struct SearchListView: View {
    
    var items: [SearchResultViewItem] = []
    
    var body: some View {
        List(items, id: \.sport.ordinal) { key in
            
            let sectionTitle: String = {
                switch key.sport {
                case .americanfootball:
                    return "sport_american_football"
                case .baseball:
                    return "sport_baseball"
                case .football:
                    return "sport_football"
                case .hockey:
                    return "sport_hockey"
                case .tennis:
                    return "sport_tennis"
                case .basketball:
                    return "sport_basketball"
                case .handball:
                    return "sport_handball"
                case .rugby:
                    return "sport_rugby"
                case .floorball:
                    return "sport_floorball"
                default:
                    return ""
                }
            }()
            
            Section(header: Text(sectionTitle.localized)) {
                ForEach(key.results, id: \.id) { item in
                    NavigationLink(destination: SearchDetailScreen(item: item)) {
                        SearchListRowView(item: item)
                    }
                }
            }
        }.listStyle(PlainListStyle())
    }
}

struct SearchListView_Previews: PreviewProvider {
    static var previews: some View {
        SearchListView(
            items: []
        )
    }
}
