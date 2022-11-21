//
//  SearchBarView.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 19.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import LivesportKit

struct SearchBarView: View {
    @Binding public var searchQuery: String
    public var onSearchClick: () -> Void
    
    var body: some View {
        HStack {
            SearchTextField(title: Strings.shared.getString(id: "enter_your_search_request"), text: $searchQuery)
            Spacer(minLength: 16)
            Button(Strings.shared.getString(id: "search_bar_button"), action: onSearchClick)
        }
    }
}
